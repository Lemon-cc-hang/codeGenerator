package ${package_config};

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 *     Mybatis-plus配置
 * </p>
 <#if author??>
 * @author ${author}
</#if>
 * @since ${date}
 */

@Configuration
public class MyBatisPlusConfig {

    /**
     * 注册分页插件(新版：3.4.0)
     * @return MybatisPlusInterceptor
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor(){
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        // DbType：数据库类型(根据类型获取应使用的分页方言)
        // 分页插件
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }

    /**
     * 注册分页插件(旧版：3.0.5)
     * @return PaginationInterceptor
     */
    // @Bean
    // public PaginationInterceptor paginationInterceptor(){
    //     return new PaginationInterceptor();
    // }
}
