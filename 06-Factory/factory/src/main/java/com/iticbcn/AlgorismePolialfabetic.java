package com.iticbcn;

import com.iticbcn.xifratge.XifradorPolialfabetic;

public class AlgorismePolialfabetic extends AlgorismeFactory {

    @Override
    public Xifrador creaXifrador() {
        return new XifradorPolialfabetic();
    }
    
}
