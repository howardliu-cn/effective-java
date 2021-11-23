package cn.howardliu.tutorials.design.pattern.strategy;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * HowardLiu <howardliu1988@163.com>
 * Created on 2021/11/15 23:45
 */
public class StrategyContext {
    private static final Map<String, PayStrategy> PAY_STRATEGY_MAP = new HashMap<>();

    static {
        final AlipayPayStrategy alipayPayStrategy = new AlipayPayStrategy();
        final WxpayPayStrategy wxpayPayStrategy = new WxpayPayStrategy();

        PAY_STRATEGY_MAP.put(alipayPayStrategy.payType(), alipayPayStrategy);
        PAY_STRATEGY_MAP.put(wxpayPayStrategy.payType(), wxpayPayStrategy);
    }

    public void pay(String payType, BigDecimal amount) {
        final PayStrategy payStrategy = PAY_STRATEGY_MAP.get(payType);
        payStrategy.callPay(amount);
    }
}
