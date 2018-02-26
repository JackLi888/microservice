package com.xmair.restapi.controller;
import com.xmair.core.entity.framedb.TbEmpData;
import com.xmair.core.mapper.framedb.TbEmpDataMapper;
import com.xmair.core.util.ResultBean;
import com.xmair.core.util.ResultCodeEnum;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import com.xmair.restapi.apiversion.ApiVersion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

/**
* <p>
    * </p>
*
* @author wuzuquan
* @date 2018-02-26 11:21:34
* @version
*/
@RestController
@RequestMapping(value = "/tbempdata")
@ApiVersion(1)
public class TbEmpDataController {

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private HttpServletResponse response;
    @Autowired
    private TbEmpDataMapper mapper;


    @ApiOperation(value="获取单条记录", notes="根据url的id来获取详细信息")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true)
    @RequestMapping(value = "/get",method = RequestMethod.GET)
    public ResultBean<TbEmpData> get(String id){
        TbEmpData item=  mapper.selectByPrimaryKey(id);
        if(item!=null){
            return new ResultBean<TbEmpData>(item);
        }else {
            return new ResultBean<TbEmpData>(ResultCodeEnum.FAILTURE.toString(),"找不到该记录",null);
        }
    }


    @RequestMapping(value = "/getlist",method = RequestMethod.GET)
    public ResultBean<List<TbEmpData>> getList(){
        List<TbEmpData> list=  mapper.selectAll();
        ResultBean<List<TbEmpData>> resultBean=new ResultBean<List<TbEmpData>>(list);
        return  resultBean;
    }

    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public ResultBean<Object> create(@Validated TbEmpData item, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            List<ObjectError>  list = bindingResult.getAllErrors();
            return new ResultBean<Object>(ResultCodeEnum.FAILTURE.toString(),"参数校验失败",list);

        }
        int  result= mapper.insert(item);
        ResultBean<Object> resultBean=new ResultBean<Object>(result);
        return  resultBean;
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public ResultBean<Integer> update(@Validated TbEmpData item){
        int  result=  mapper.updateByPrimaryKey(item);
        ResultBean<Integer> resultBean=new ResultBean<Integer>(result);
        return  resultBean;
    }

    @RequestMapping(value = "/deleteByID",method = RequestMethod.POST)
    public ResultBean<Integer> delete(String id){
        int  result=  mapper.deleteByPrimaryKey(id);
        ResultBean<Integer> resultBean=new ResultBean<Integer>(result);
        return  resultBean;
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public ResultBean<Integer> delete(TbEmpData item){
        int  result=  mapper.updateByPrimaryKey(item);
        ResultBean<Integer> resultBean=new ResultBean<Integer>(result);
        return  resultBean;
    }

}
