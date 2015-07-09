/*jadclipse*/// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) radix(10) lradix(10) 
// Source File Name:   BeanUtilsBean.java

package org.apache.commons.beanutils;

import java.beans.IndexedPropertyDescriptor;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class BeanUtilsBean
{

    public static synchronized BeanUtilsBean getInstance()
    {
        return (BeanUtilsBean)beansByClassLoader.get();
    }

    public static synchronized void setInstance(BeanUtilsBean newInstance)
    {
        beansByClassLoader.set(newInstance);
    }

    public BeanUtilsBean()
    {
        this(new ConvertUtilsBean(), new PropertyUtilsBean());
    }

    public BeanUtilsBean(ConvertUtilsBean convertUtilsBean, PropertyUtilsBean propertyUtilsBean)
    {
        log = LogFactory.getLog(org.apache.commons.beanutils.BeanUtils.class);
        this.convertUtilsBean = convertUtilsBean;
        this.propertyUtilsBean = propertyUtilsBean;
    }

    public Object cloneBean(Object bean)
        throws IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException
    {
        if(log.isDebugEnabled())
            log.debug("Cloning bean: " + bean.getClass().getName());
        Class clazz = bean.getClass();
        Object newBean = null;
        if(bean instanceof DynaBean)
            newBean = ((DynaBean)bean).getDynaClass().newInstance();
        else
            newBean = bean.getClass().newInstance();
        getPropertyUtils().copyProperties(newBean, bean);
        return newBean;
    }

    public void copyProperties(Object dest, Object orig)
        throws IllegalAccessException, InvocationTargetException
    {
        if(dest == null)
            throw new IllegalArgumentException("No destination bean specified");
        if(orig == null)
            throw new IllegalArgumentException("No origin bean specified");
        if(log.isDebugEnabled())
            log.debug("BeanUtils.copyProperties(" + dest + ", " + orig + ")");
        if(orig instanceof DynaBean)
        {
            DynaProperty origDescriptors[] = ((DynaBean)orig).getDynaClass().getDynaProperties();
            for(int i = 0; i < origDescriptors.length; i++)
            {
                String name = origDescriptors[i].getName();
                if(getPropertyUtils().isWriteable(dest, name))
                {
                    Object value = ((DynaBean)orig).get(name);
                    copyProperty(dest, name, value);
                }
            }

        } else
        if(orig instanceof Map)
        {
            for(Iterator names = ((Map)orig).keySet().iterator(); names.hasNext();)
            {
                String name = (String)names.next();
                if(getPropertyUtils().isWriteable(dest, name))
                {
                    Object value = ((Map)orig).get(name);
                    copyProperty(dest, name, value);
                }
            }

        } else
        {
            PropertyDescriptor origDescriptors[] = getPropertyUtils().getPropertyDescriptors(orig);
            for(int i = 0; i < origDescriptors.length; i++)
            {
                String name = origDescriptors[i].getName();
                if(!"class".equals(name) && getPropertyUtils().isReadable(orig, name) && getPropertyUtils().isWriteable(dest, name))
                    try
                    {
                        Object value = getPropertyUtils().getSimpleProperty(orig, name);
                        copyProperty(dest, name, value);
                    }
                    catch(NoSuchMethodException e) { }
            }

        }
    }

    public void copyProperty(Object bean, String name, Object value)
        throws IllegalAccessException, InvocationTargetException
    {
        if(log.isTraceEnabled())
        {
            StringBuffer sb = new StringBuffer("  copyProperty(");
            sb.append(bean);
            sb.append(", ");
            sb.append(name);
            sb.append(", ");
            if(value == null)
                sb.append("<NULL>");
            else
            if(value instanceof String)
                sb.append((String)value);
            else
            if(value instanceof String[])
            {
                String values[] = (String[])value;
                sb.append('[');
                for(int i = 0; i < values.length; i++)
                {
                    if(i > 0)
                        sb.append(',');
                    sb.append(values[i]);
                }

                sb.append(']');
            } else
            {
                sb.append(value.toString());
            }
            sb.append(')');
            log.trace(sb.toString());
        }
        Object target = bean;
        int delim = name.lastIndexOf('.');
        if(delim >= 0)
        {
            try
            {
                target = getPropertyUtils().getProperty(bean, name.substring(0, delim));
            }
            catch(NoSuchMethodException e)
            {
                return;
            }
            name = name.substring(delim + 1);
            if(log.isTraceEnabled())
            {
                log.trace("    Target bean = " + target);
                log.trace("    Target name = " + name);
            }
        }
        String propName = null;
        Class type = null;
        int index = -1;
        String key = null;
        propName = name;
        int i = propName.indexOf('[');
        if(i >= 0)
        {
            int k = propName.indexOf(']');
            try
            {
                index = Integer.parseInt(propName.substring(i + 1, k));
            }
            catch(NumberFormatException e) { }
            propName = propName.substring(0, i);
        }
        int j = propName.indexOf('(');
        if(j >= 0)
        {
            int k = propName.indexOf(')');
            try
            {
                key = propName.substring(j + 1, k);
            }
            catch(IndexOutOfBoundsException e) { }
            propName = propName.substring(0, j);
        }
        if(target instanceof DynaBean)
        {
            DynaClass dynaClass = ((DynaBean)target).getDynaClass();
            DynaProperty dynaProperty = dynaClass.getDynaProperty(propName);
            if(dynaProperty == null)
                return;
            type = dynaProperty.getType();
        } else
        {
            PropertyDescriptor descriptor = null;
            try
            {
                descriptor = getPropertyUtils().getPropertyDescriptor(target, name);
                if(descriptor == null)
                    return;
            }
            catch(NoSuchMethodException e)
            {
                return;
            }
            type = descriptor.getPropertyType();
            if(type == null)
            {
                if(log.isTraceEnabled())
                    log.trace("    target type for property '" + propName + "' is null, so skipping ths setter");
                return;
            }
        }
        if(log.isTraceEnabled())
            log.trace("    target propName=" + propName + ", type=" + type + ", index=" + index + ", key=" + key);
        if(index >= 0)
        {
            Converter converter = getConvertUtils().lookup(type.getComponentType());
            if(converter != null)
            {
                log.trace("        USING CONVERTER " + converter);
                value = converter.convert(type, value);
            }
            try
            {
                getPropertyUtils().setIndexedProperty(target, propName, index, value);
            }
            catch(NoSuchMethodException e)
            {
                throw new InvocationTargetException(e, "Cannot set " + propName);
            }
        } else
        if(key != null)
        {
            try
            {
                getPropertyUtils().setMappedProperty(target, propName, key, value);
            }
            catch(NoSuchMethodException e)
            {
                throw new InvocationTargetException(e, "Cannot set " + propName);
            }
        } else
        {
            Converter converter = getConvertUtils().lookup(type);
            if(converter != null)
            {
                log.trace("        USING CONVERTER " + converter);
                value = converter.convert(type, value);
            }
            try
            {
                getPropertyUtils().setSimpleProperty(target, propName, value);
            }
            catch(NoSuchMethodException e)
            {
                throw new InvocationTargetException(e, "Cannot set " + propName);
            }
        }
    }

    public Map describe(Object bean)
        throws IllegalAccessException, InvocationTargetException, NoSuchMethodException
    {
        if(bean == null)
            return new HashMap();
        if(log.isDebugEnabled())
            log.debug("Describing bean: " + bean.getClass().getName());
        Map description = new HashMap();
        if(bean instanceof DynaBean)
        {
            DynaProperty descriptors[] = ((DynaBean)bean).getDynaClass().getDynaProperties();
            for(int i = 0; i < descriptors.length; i++)
            {
                String name = descriptors[i].getName();
                description.put(name, getProperty(bean, name));
            }

        } else
        {
            PropertyDescriptor descriptors[] = getPropertyUtils().getPropertyDescriptors(bean);
            for(int i = 0; i < descriptors.length; i++)
            {
                String name = descriptors[i].getName();
                if(descriptors[i].getReadMethod() != null)
                    description.put(name, getProperty(bean, name));
            }

        }
        return description;
    }

    public String[] getArrayProperty(Object bean, String name)
        throws IllegalAccessException, InvocationTargetException, NoSuchMethodException
    {
        Object value = getPropertyUtils().getProperty(bean, name);
        if(value == null)
            return null;
        if(value instanceof Collection)
        {
            ArrayList values = new ArrayList();
            for(Iterator items = ((Collection)value).iterator(); items.hasNext();)
            {
                Object item = items.next();
                if(item == null)
                    values.add((String)null);
                else
                    values.add(getConvertUtils().convert(item));
            }

            return (String[])values.toArray(new String[values.size()]);
        }
        if(value.getClass().isArray())
        {
            int n = Array.getLength(value);
            String results[] = new String[n];
            for(int i = 0; i < n; i++)
            {
                Object item = Array.get(value, i);
                if(item == null)
                    results[i] = null;
                else
                    results[i] = getConvertUtils().convert(item);
            }

            return results;
        } else
        {
            String results[] = new String[1];
            results[0] = value.toString();
            return results;
        }
    }

    public String getIndexedProperty(Object bean, String name)
        throws IllegalAccessException, InvocationTargetException, NoSuchMethodException
    {
        Object value = getPropertyUtils().getIndexedProperty(bean, name);
        return getConvertUtils().convert(value);
    }

    public String getIndexedProperty(Object bean, String name, int index)
        throws IllegalAccessException, InvocationTargetException, NoSuchMethodException
    {
        Object value = getPropertyUtils().getIndexedProperty(bean, name, index);
        return getConvertUtils().convert(value);
    }

    public String getMappedProperty(Object bean, String name)
        throws IllegalAccessException, InvocationTargetException, NoSuchMethodException
    {
        Object value = getPropertyUtils().getMappedProperty(bean, name);
        return getConvertUtils().convert(value);
    }

    public String getMappedProperty(Object bean, String name, String key)
        throws IllegalAccessException, InvocationTargetException, NoSuchMethodException
    {
        Object value = getPropertyUtils().getMappedProperty(bean, name, key);
        return getConvertUtils().convert(value);
    }

    public String getNestedProperty(Object bean, String name)
        throws IllegalAccessException, InvocationTargetException, NoSuchMethodException
    {
        Object value = getPropertyUtils().getNestedProperty(bean, name);
        return getConvertUtils().convert(value);
    }

    public String getProperty(Object bean, String name)
        throws IllegalAccessException, InvocationTargetException, NoSuchMethodException
    {
        return getNestedProperty(bean, name);
    }

    public String getSimpleProperty(Object bean, String name)
        throws IllegalAccessException, InvocationTargetException, NoSuchMethodException
    {
        Object value = getPropertyUtils().getSimpleProperty(bean, name);
        return getConvertUtils().convert(value);
    }

    public void populate(Object bean, Map properties)
        throws IllegalAccessException, InvocationTargetException
    {
        if(bean == null || properties == null)
            return;
        if(log.isDebugEnabled())
            log.debug("BeanUtils.populate(" + bean + ", " + properties + ")");
        for(Iterator names = properties.keySet().iterator(); names.hasNext();)
        {
            String name = (String)names.next();
            if(name != null)
            {
                Object value = properties.get(name);
                setProperty(bean, name, value);
            }
        }

    }

    public void setProperty(Object bean, String name, Object value)
        throws IllegalAccessException, InvocationTargetException
    {
        if(log.isTraceEnabled())
        {
            StringBuffer sb = new StringBuffer("  setProperty(");
            sb.append(bean);
            sb.append(", ");
            sb.append(name);
            sb.append(", ");
            if(value == null)
                sb.append("<NULL>");
            else
            if(value instanceof String)
                sb.append((String)value);
            else
            if(value instanceof String[])
            {
                String values[] = (String[])value;
                sb.append('[');
                for(int i = 0; i < values.length; i++)
                {
                    if(i > 0)
                        sb.append(',');
                    sb.append(values[i]);
                }

                sb.append(']');
            } else
            {
                sb.append(value.toString());
            }
            sb.append(')');
            log.trace(sb.toString());
        }
        Object target = bean;
        int delim = findLastNestedIndex(name);
        if(delim >= 0)
        {
            try
            {
                target = getPropertyUtils().getProperty(bean, name.substring(0, delim));
            }
            catch(NoSuchMethodException e)
            {
                return;
            }
            name = name.substring(delim + 1);
            if(log.isTraceEnabled())
            {
                log.trace("    Target bean = " + target);
                log.trace("    Target name = " + name);
            }
        }
        String propName = null;
        Class type = null;
        int index = -1;
        String key = null;
        propName = name;
        int i = propName.indexOf('[');
        if(i >= 0)
        {
            int k = propName.indexOf(']');
            try
            {
                index = Integer.parseInt(propName.substring(i + 1, k));
            }
            catch(NumberFormatException e) { }
            propName = propName.substring(0, i);
        }
        int j = propName.indexOf('(');
        if(j >= 0)
        {
            int k = propName.indexOf(')');
            try
            {
                key = propName.substring(j + 1, k);
            }
            catch(IndexOutOfBoundsException e) { }
            propName = propName.substring(0, j);
        }
        if(target instanceof DynaBean)
        {
            DynaClass dynaClass = ((DynaBean)target).getDynaClass();
            DynaProperty dynaProperty = dynaClass.getDynaProperty(propName);
            if(dynaProperty == null)
                return;
            type = dynaProperty.getType();
        } else
        {
            PropertyDescriptor descriptor = null;
            try
            {
                descriptor = getPropertyUtils().getPropertyDescriptor(target, name);
                if(descriptor == null)
                    return;
            }
            catch(NoSuchMethodException e)
            {
                return;
            }
            if(descriptor instanceof MappedPropertyDescriptor)
            {
                if(((MappedPropertyDescriptor)descriptor).getMappedWriteMethod() == null)
                {
                    if(log.isDebugEnabled())
                        log.debug("Skipping read-only property");
                    return;
                }
                type = ((MappedPropertyDescriptor)descriptor).getMappedPropertyType();
            } else
            if(descriptor instanceof IndexedPropertyDescriptor)
            {
                if(((IndexedPropertyDescriptor)descriptor).getIndexedWriteMethod() == null)
                {
                    if(log.isDebugEnabled())
                        log.debug("Skipping read-only property");
                    return;
                }
                type = ((IndexedPropertyDescriptor)descriptor).getIndexedPropertyType();
            } else
            {
                if(descriptor.getWriteMethod() == null)
                {
                    if(log.isDebugEnabled())
                        log.debug("Skipping read-only property");
                    return;
                }
                type = descriptor.getPropertyType();
            }
        }
        Object newValue = null;
        if(type.isArray() && index < 0)
        {
            if(value == null)
            {
                String values[] = new String[1];
                values[0] = (String)value;
                newValue = getConvertUtils().convert((String[])values, type);
            } else
            if(value instanceof String)
            {
                String values[] = new String[1];
                values[0] = (String)value;
                newValue = getConvertUtils().convert((String[])values, type);
            } else
            if(value instanceof String[])
                newValue = getConvertUtils().convert((String[])value, type);
            else
                newValue = value;
        } else
        if(type.isArray())
        {
            if(value instanceof String)
                newValue = getConvertUtils().convert((String)value, type.getComponentType());
            else
            if(value instanceof String[])
                newValue = getConvertUtils().convert(((String[])value)[0], type.getComponentType());
            else
                newValue = value;
        } else
        if((value instanceof String) || value == null)
            newValue = getConvertUtils().convert((String)value, type);
        else
        if(value instanceof String[])
            newValue = getConvertUtils().convert(((String[])value)[0], type);
        else
        if(getConvertUtils().lookup(value.getClass()) != null)
            newValue = getConvertUtils().convert(value.toString(), type);
        else
            newValue = value;
        try
        {
            if(index >= 0)
                getPropertyUtils().setIndexedProperty(target, propName, index, newValue);
            else
            if(key != null)
                getPropertyUtils().setMappedProperty(target, propName, key, newValue);
            else
                getPropertyUtils().setProperty(target, propName, newValue);
        }
        catch(NoSuchMethodException e)
        {
            throw new InvocationTargetException(e, "Cannot set " + propName);
        }
    }

    private int findLastNestedIndex(String expression)
    {
        int bracketCount = 0;
        for(int i = expression.length() - 1; i >= 0; i--)
        {
            char at = expression.charAt(i);
            switch(at)
            {
            default:
                break;

            case 46: // '.'
                if(bracketCount < 1)
                    return i;
                break;

            case 40: // '('
            case 91: // '['
                bracketCount--;
                break;

            case 41: // ')'
            case 93: // ']'
                bracketCount++;
                break;
            }
        }

        return -1;
    }

    public ConvertUtilsBean getConvertUtils()
    {
        return convertUtilsBean;
    }

    public PropertyUtilsBean getPropertyUtils()
    {
        return propertyUtilsBean;
    }

    private static final ContextClassLoaderLocal beansByClassLoader = new ContextClassLoaderLocal() {

        protected Object initialValue()
        {
            return new BeanUtilsBean();
        }

    }
;
    private Log log;
    private ConvertUtilsBean convertUtilsBean;
    private PropertyUtilsBean propertyUtilsBean;

}



/***** DECOMPILATION REPORT *****

	DECOMPILED FROM: E:\hotchpotch\MyWork\JavaSE\lib\commons-beanutils-1.7.0.jar


	TOTAL TIME: 40 ms


	JAD REPORTED MESSAGES/ERRORS:


	EXIT STATUS:	0


	CAUGHT EXCEPTIONS:

 ********************************/