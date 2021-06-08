package com.zjt.graduation.mservice.api;

import com.zjt.graduation.common.dto.article.ArticleRequestDTO;
import com.zjt.graduation.common.entity.Role;
import com.zjt.graduation.common.query.ComplexQueryRequest;
import com.zjt.graduation.common.response.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@Api(tags = "角色APi",value = "角色相关")
@RequestMapping("/mservice/role")
public interface RoleApi {
    @ApiOperation(value = "条件获取角色")
    @PostMapping("/queryRole")
    CommonResult queryRole(@RequestBody(required =  false) ComplexQueryRequest requestDTO);

    @ApiOperation(value = "添加角色")
    @PostMapping("/addRole")
    CommonResult addRole(@RequestBody(required = false)Role role);

    @ApiOperation(value = "修改角色")
    @PostMapping("/updateRole")
    CommonResult updateRole(@RequestBody(required = false)Role role);

    @ApiOperation(value = "删除角色")
    @GetMapping("/deleteRole")
    CommonResult deleteRole(@RequestParam Long id);




}
