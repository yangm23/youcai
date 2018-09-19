package com.youcai.user.controller;



import com.youcai.user.dataobject.Product;
import com.youcai.user.service.CategoryService;
import com.youcai.user.service.ProductService;
import com.youcai.user.utils.ResultVOUtils;
import com.youcai.user.vo.ResultVO;
import com.youcai.user.vo.product.OneV0;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product")
public class ProductRestController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/findOne")
    public ResultVO<OneV0> findOne(
            @RequestParam String id
    ){
        Product product = productService.findOne(id);
        if (product == null){
            return ResultVOUtils.error("此产品不存在");
        }

        Map<String, String> categoryMap = categoryService.findAllInMap();

        OneV0 OneVO = new OneV0();
        BeanUtils.copyProperties(product, OneVO);

        OneVO.setPCodeName(categoryMap.get(OneVO.getPCode()));

        return ResultVOUtils.success(OneVO);
    }

    @GetMapping("/findPage")
    public ResultVO<Page<OneV0>> findPage(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size
    ){
        /*------------ 1.准备 -------------*/
        page = page<0 ? 0:page;
        size = size<=0 ? 10:size;
        Pageable pageable = new PageRequest(page, size);

        /*------------ 2.查询 -------------*/
        Page<Product> productPage = productService.findAll(pageable);
        Map<String, String> categoryMap = categoryService.findAllInMap();

        /*------------ 3.数据拼装 -------------*/
        List<OneV0> OneVOS = productPage.getContent().stream().map(e -> {
            OneV0 OneVO = new OneV0();
            BeanUtils.copyProperties(e, OneVO);

            OneVO.setPCodeName(categoryMap.get(OneVO.getPCode()));

            return OneVO;
        }).collect(Collectors.toList());
        Page<OneV0> OneVOPage = new PageImpl<OneV0>(OneVOS, pageable, productPage.getTotalElements());

        return ResultVOUtils.success(OneVOPage);
    }


    @GetMapping("/findPageByNameLikeAndCodeIn")
    public ResultVO<Page<OneV0>> findByPCodeIn(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size,
            @RequestParam(required = false) String name,
            @RequestParam(required = false, name = "PCodes") String codeStr
    ){
        /*------------ 1.准备 -------------*/
        // 分页
        page = page<0 ? 0:page;
        size = size<=0 ? 10:size;
        Pageable pageable = new PageRequest(page, size);
        // 产品大类编码列表
        List<String> codes = null;
        if (StringUtils.isEmpty(codeStr) == false){
            String[] codeArr = codeStr.split(",");
            codes = Arrays.asList(codeArr);
        }

        /*------------ 2.查询 -------------*/
        Page<Product> productPage = productService.findBy(name, codes, pageable);
        Map<String, String> categoryMap = categoryService.findAllInMap();

        /*------------ 3.数据拼装 -------------*/
        List<OneV0> OneVOS = productPage.getContent().stream().map(e -> {
            OneV0 OneVO = new OneV0();
            BeanUtils.copyProperties(e, OneVO);
            OneVO.setPCodeName(categoryMap.get(OneVO.getPCode()));
            return OneVO;
        }).collect(Collectors.toList());
        Page<OneV0> OneVOPage = new PageImpl<OneV0>(OneVOS, pageable, productPage.getTotalElements());

        return ResultVOUtils.success(OneVOPage);
    }
}
