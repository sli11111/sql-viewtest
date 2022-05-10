package com.ysmjjsy.goya.base;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ysmjjsy.goya.pojo.vo.ResultVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author goya
 * @create 2022-03-18 22:16
 */
public abstract class BaseController<T extends BaseEntity> {

    private IService<T> service;

    public void baseController(IService<T> service) {
        this.service = service;
    }

    /**
     * 获取实际service
     *
     * @return service
     */
    public abstract IService<T> getService();

    @ApiOperation(value = "新增(通用)", notes = "新增(通用)")
    @PostMapping("/add")
    public ResultVO<T> add(@RequestBody @Valid T t) {

        boolean res = this.getService().save(t);
        if (res) {
            return ResultVO.succeed("新增成功!", t);
        }
        return ResultVO.fail("新增失败!", t);
    }

    @ApiOperation(value = "编辑(通用)", notes = "编辑(通用)")
    @PutMapping("/update")
    public ResultVO<T> update(@RequestBody @Valid T t) {
        boolean res = this.getService().updateById(t);
        if (res) {
            return ResultVO.succeed("编辑成功!", t);
        }
        return ResultVO.fail("编辑失败!", t);
    }


    @ApiOperation(value = "删除(通用)", notes = "删除(通用)")
    @DeleteMapping("/del/{id}")
    public ResultVO del(@PathVariable("id") String id) {
        boolean res = this.getService().removeById(id);
        if (res) {
            return ResultVO.succeed("删除成功!", id);
        }
        return ResultVO.fail("删除失败!", id);
    }

    @ApiOperation(value = "查询详情(通用)", notes = "查询详情(通用)")
    @GetMapping("/details")
    public ResultVO<T> details(@RequestParam(required = true) String id) {
        T t = this.getService().getById(id);
        return ResultVO.succeed("查询成功!", t);
    }


    @ApiOperation(value = "分页查询(通用)", notes = "分页查询(通用)")
    @GetMapping("/list")
    public ResultVO<Page<T>> list(@RequestParam(defaultValue = "1") Integer pageNum,
                                  @RequestParam(defaultValue = "10") Integer pageSize) {
        Page<T> tPage = new Page<T>(pageNum, pageSize);
        tPage.setOrders(OrderItem.descs("CREATE_TIME"));
        Page<T> page = this.getService().page(tPage);
        return ResultVO.succeed("查询成功!", page);
    }


    @ApiOperation(value = "条件查询(通用)", notes = "条件查询(通用)")
    @GetMapping("/query-condition-list")
    public ResultVO<List<T>> queryConditionList(T t) {
        QueryWrapper<T> queryWrapper = new QueryWrapper<>();
        queryWrapper.setEntity(t);
        List<T> list = this.getService().list(queryWrapper);
        return ResultVO.succeed("查询成功!", list);
    }

    @ApiOperation(value = "数据列表查询(通用)", notes = "数据列表查询(通用)")
    @GetMapping("/query-all-list")
    public ResultVO<List<T>> queryAllList() {
        List<T> list = this.getService().list();
        return ResultVO.succeed("查询成功!", list);
    }
}
