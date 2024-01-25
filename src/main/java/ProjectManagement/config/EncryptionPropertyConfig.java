package ProjectManagement.config;

import com.ulisesbocchio.jasyptspringboot.EncryptablePropertyResolver;
import io.micrometer.common.util.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EncryptionPropertyConfig {

    @Bean(name = "encryptablePropertyResolver")
    public EncryptablePropertyResolver encryptablePropertyResolver() {
        return new EncryptionPropertyResolver();
    }

    class EncryptionPropertyResolver implements EncryptablePropertyResolver {

        @Override
        public String resolvePropertyValue(String value) {
            if (StringUtils.isEmpty(value)) {
                return value;
            }
            // 值以DES@开头的均为DES加密,需要解密
            if (value.startsWith("DES@")) {
                return resolveDESValue(value.substring(4));
            }
            // 不需要解密的值直接返回
            return value;
        }

        private String resolveDESValue(String value) {
            // 自定义DES密文解密
            return DesUtil.decrypt(value, DesUtil.KEY);
        }

    }
}

