package cn.com.xl.lock;

import java.util.concurrent.CyclicBarrier;

class SyncTest extends TestTemplate{     
    public SyncTest(String _id,int _round,int _threadNum,CyclicBarrier _cb){     
        super( _id, _round, _threadNum, _cb);     
    }     
    @Override    
    /**   
     * synchronized关键字不在方法签名里面，所以不涉及重载问题   
     */    
    synchronized long  getValue() {     
        return super.countValue;     
    }     
    @Override    
    synchronized void  sumValue() {     
        super.countValue+=preInit[index++%round];     
    }     
} 