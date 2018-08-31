package com.xmair.restapi.controller;
import com.xmair.core.entity.framedb.TbEmpData;
import com.xmair.core.mapper.framedb.TbEmpDataMapper;
import com.xmair.core.util.ResultBean;
import com.xmair.core.exception.ExceptionEnum;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiParam;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.validation.annotation.Validated;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import com.xmair.restapi.apiversion.ApiVersion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.xmair.core.util.JsonUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
* <p>
    * </p>
*
* @author wuzuquan
* @date 2018-06-28 09:09:00
* @version
*/
@RestController
@RequestMapping(value = "/tbempdata")
@ApiVersion(1)
public class TbEmpDataController {

    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private HttpServletResponse response;
    @Autowired
    private TbEmpDataMapper mapper;


    @ApiOperation(value="获取单条记录", notes="根据url的id来获取详细信息")
    @RequestMapping(value = "/get",method = RequestMethod.GET)
    public ResultBean<TbEmpData> get(String id){
        
        TbEmpData item=  mapper.selectByPrimaryKey(id);
        if(item!=null){
            return new ResultBean<TbEmpData>(item);
        }else {
            return new ResultBean<TbEmpData>(ExceptionEnum.RESOURCE_NOT_FOUND,null,"找不到该记录",null);
        }
    }



    @RequestMapping(value = "/getlist",method = RequestMethod.GET)
    public ResultBean<List<TbEmpData>> getList(){

        List<TbEmpData> list=  mapper.selectAll();
        ResultBean<List<TbEmpData>> resultBean=new ResultBean<List<TbEmpData>>(list);
        return  resultBean;
    }

    @RequestMapping(value = "/create",method = RequestMethod.POST)
      public ResultBean<String> create(@ApiParam(name="item",value="产品临时对象",required = true)
                                           @RequestBody  @Validated  TbEmpData item){
        int  result= mapper.insertSelective(item);
        logger.info("create TbEmpData success,record,{}"+ JsonUtil.bean2Json(item));
        ResultBean<String> resultBean=new ResultBean<String>("");
        return  resultBean;
    }



    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public ResultBean<String> update(@RequestBody @Validated TbEmpData item){
        int  result=  mapper.updateByPrimaryKeySelective(item);
        logger.info("update TbEmpData success,record,{}"+ JsonUtil.bean2Json(item));
        ResultBean<String> resultBean=new ResultBean<String>("");
        return  resultBean;
    }

    @RequestMapping(value = "/deleteByID",method = RequestMethod.POST)
    public ResultBean<Integer> delete(String id){
        int  result=  mapper.deleteByPrimaryKey(id);
        logger.info("delete TbEmpData success,record id,{}"+ id);
        ResultBean<Integer> resultBean=new ResultBean<Integer>(result);
        return  resultBean;
    }


    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public ResultBean<Integer> delete(@RequestBody @Validated TbEmpData item){
        int  result=  mapper.delete(item);
        ResultBean<Integer> resultBean=new ResultBean<Integer>(result);
        return  resultBean;
    }

}

