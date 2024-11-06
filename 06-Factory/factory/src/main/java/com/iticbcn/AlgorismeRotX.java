package com.iticbcn;

import com.iticbcn.xifratge.XifradorRotX;

public class AlgorismeRotX extends AlgorismeFactory {

    @Override
    public Xifrador creaXifrador() {
        return new XifradorRotX();
    }
    
}
