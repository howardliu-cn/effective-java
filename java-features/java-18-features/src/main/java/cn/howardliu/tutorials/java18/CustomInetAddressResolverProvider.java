package cn.howardliu.tutorials.java18;

import static java.net.spi.InetAddressResolver.LookupPolicy.IPV4;
import static java.net.spi.InetAddressResolver.LookupPolicy.IPV6;
import static java.net.spi.InetAddressResolver.LookupPolicy.IPV6_FIRST;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.net.spi.InetAddressResolver;
import java.net.spi.InetAddressResolver.LookupPolicy;
import java.net.spi.InetAddressResolverProvider;
import java.util.Arrays;
import java.util.ServiceLoader;
import java.util.stream.Stream;

/**
 * @author 看山 howardliu.cn <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 * Created on 2024-08-03
 */
public class CustomInetAddressResolverProvider extends InetAddressResolverProvider {
    public static void main(String[] args) throws UnknownHostException {
        // 注册自定义的InetAddressResolverProvider
        ServiceLoader<InetAddressResolverProvider> resolvers = ServiceLoader.load(InetAddressResolverProvider.class);
        final InetAddressResolverProvider inetAddressResolverProvider = resolvers.findFirst().orElse(null);
        if (inetAddressResolverProvider == null) {
            System.err.println("No InetAddressResolverProvider found");
        }
        InetAddressResolver customResolver = inetAddressResolverProvider.get(null);

        InetAddress address = customResolver.lookupByName("localhost", LookupPolicy.of(IPV6_FIRST))
                .findFirst()
                .orElse(null);
        System.out.println("IP Address: " + address);
    }

    @Override
    public InetAddressResolver get(Configuration configuration) {
        return new InetAddressResolver() {
            @Override
            public Stream<InetAddress> lookupByName(String host, LookupPolicy lookupPolicy) throws UnknownHostException {
                // 这里可以实现自定义的解析逻辑
                // 例如，返回一个固定的 IP 地址列表作为示例
                return Stream.of(
                                // IPv4 loopback
                                InetAddress.getByAddress(host, new byte[]{127, 0, 0, 1}),
                                // IPv6 loopback
                                InetAddress.getByAddress(host, new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1})
                        )
                        .filter(address -> lookupPolicy.characteristics() != IPV4 || address instanceof java.net.Inet4Address)
                        .filter(address -> lookupPolicy.characteristics() != IPV6 || address instanceof java.net.Inet6Address);
            }

            @Override
            public String lookupByAddress(byte[] addr) throws UnknownHostException {
                // 这里可以实现自定义的解析逻辑
                // 例如，对于特定的 IP 地址，返回一个固定的主机名作为示例
                if (addr.length == 4 && addr[0] == -1 && addr[1] == 0 && addr[2] == 0 && addr[3] == 1) {
                    // IPv4 loopback address
                    return "localhost";
                } else if (addr.length == 16 && addr[15] == 1) {
                    // IPv6 loopback address
                    return "localhost";
                }
                throw new UnknownHostException("Unknown host for address: " + Arrays.toString(addr));
            }
        };
    }

    @Override
    public String name() {
        return "custom-inet-address-resolver-provider";
    }
}
