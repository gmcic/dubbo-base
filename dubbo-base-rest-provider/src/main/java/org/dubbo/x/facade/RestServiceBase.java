package org.dubbo.x.facade;

import io.swagger.annotations.ApiParam;
import org.dubbo.x.dto.PageSearch;
import org.dubbo.x.dto.RestResult;
import org.dubbo.x.service.CURDService;
import org.dubbo.x.util.ConstantVariable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;

import javax.ws.rs.HeaderParam;
import java.util.List;

/**
 * User: tom
 * Date: 14-12-9
 * Time: 下午3:45
 */
public abstract class RestServiceBase<T> implements CURDRestService<T>{
    private static final Logger LOGGER = LoggerFactory.getLogger(RestServiceBase.class);

    public abstract CURDService<T> getService();

    public RestResult<List<T>> list(@HeaderParam(ConstantVariable.HEADER_AUTHORIZATION_KEY) String token) {
        return RestResult.OK(getService().listAll());
    }

    @Override
    public RestResult<Page<T>> page(@HeaderParam(ConstantVariable.HEADER_AUTHORIZATION_KEY) String token, PageSearch pageSearch) {
        Page<T> page = getService().page(pageSearch);

        LOGGER.debug("pageSearch:{} => {}", pageSearch, page);

        return RestResult.OK(page);
    }

    public RestResult<T> create(@HeaderParam(ConstantVariable.HEADER_AUTHORIZATION_KEY) String token, T petRace) {
        LOGGER.debug("create or update :{}", petRace);
        return RestResult.OK(getService().createOrUpdte(petRace));
    }

    public RestResult<String> delete(@HeaderParam(ConstantVariable.HEADER_AUTHORIZATION_KEY) String token, @ApiParam("id") String uuid) {
        LOGGER.debug("delete :{}", uuid);
        getService().delete(uuid);
        return RestResult.OK("删除成功!");
    }


    @Override
    public RestResult<T> detail(@HeaderParam(ConstantVariable.HEADER_AUTHORIZATION_KEY) String token, @ApiParam("id") String uuid) {
        return RestResult.OK(getService().detail(uuid));
    }


}
