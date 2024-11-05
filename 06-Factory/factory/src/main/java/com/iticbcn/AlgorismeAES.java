package com.iticbcn;

import com.iticbcn.xifratge.XifradorAES;

public class AlgorismeAES extends AlgorismeFactory {

    @Override
    public Xifrador creaXifrador() {
        return new XifradorAES();
    }
    
}
