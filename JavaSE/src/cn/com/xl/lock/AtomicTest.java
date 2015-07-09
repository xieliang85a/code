package cn.com.xl.lock;

import java.util.concurrent.CyclicBarrier;

class AtomicTest extends TestTemplate{     
    public AtomicTest(String _id,int _round,int _threadNum,CyclicBarrier _cb){     
        super( _id, _round, _threadNum, _cb);     
    }     
    @Override    
    /**   
     * synchronized关键字不在方法签名里面，所以不涉及重载问题   
     */    
    long  getValue() {     
        return super.countValueAtmoic.get();     
    }     
    @Override    
    void  sumValue() {     
        super.countValueAtmoic.addAndGet(super.preInit[indexAtomic.get()%round]);     
    }     
}  