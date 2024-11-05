package com.iticbcn;

import com.iticbcn.xifratge.XifradorMonoalfabetic;

public class AlgorismeMonoalfabetic extends AlgorismeFactory {

    @Override
    public Xifrador creaXifrador() {
        return new XifradorMonoalfabetic();
    }
    
}
