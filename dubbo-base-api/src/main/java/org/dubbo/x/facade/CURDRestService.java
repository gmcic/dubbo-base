package org.dubbo.x.facade;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.dubbo.x.entity.PageSearch;
import org.dubbo.x.util.ConstantVariable;
import org.springframework.data.domain.Page;

import javax.ws.rs.*;
import java.util.List;

/**
 * rest 接口
 *
 * @author baiqw
 */

public interface CURDRestService<T> {
    @GET
    @ApiOperation(value = "列表")
    RestResult<List<T>> list(@HeaderParam(ConstantVariable.HEADER_AUTHORIZATION_KEY) String token);

    @POST
    @ApiOperation(value = "分页")
    RestResult<Page<T>> page(@HeaderParam(ConstantVariable.HEADER_AUTHORIZATION_KEY) String token, PageSearch pageSearch);

    @GET
    @ApiOperation(value = "详细信息")
    RestResult<T> detail(@HeaderParam(ConstantVariable.HEADER_AUTHORIZATION_KEY) String token, @ApiParam("id") @PathParam("id") String id);

    @POST
    @ApiOperation(value = "添加/修改")
    RestResult<T> create(@HeaderParam(ConstantVariable.HEADER_AUTHORIZATION_KEY) String token, T entity);

    @DELETE
    @ApiOperation(value = "删除")
    RestResult<String> delete(@HeaderParam(ConstantVariable.HEADER_AUTHORIZATION_KEY) String token, @ApiParam("id") @PathParam("id") String id);
}
