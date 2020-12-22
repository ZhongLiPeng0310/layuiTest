package com.lbxtest.layui.Service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lbxtest.layui.Service.ProductService;
import com.lbxtest.layui.entity.Product;
import com.lbxtest.layui.entity.ProductCategory;
import com.lbxtest.layui.mapper.ProductCategoryMapper;
import com.lbxtest.layui.mapper.ProductMapper;
import com.lbxtest.layui.vo.DataVO;
import com.lbxtest.layui.vo.ProductVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ProductCategoryMapper productCategoryMapper;

    @Override
    public DataVO<ProductVO> findData(Integer page,Integer limit) {
        DataVO dataVO = new DataVO();
        dataVO.setCode(0);
        dataVO.setMsg("");
//        dataVO.setCount(productMapper.selectCount(null));

        IPage<Product> productIPage = new Page<Product>(page,limit);
        IPage<Product> result =  productMapper.selectPage(productIPage,null);
        dataVO.setCount(result.getTotal());

        List<Product> productList = result.getRecords();
        List<ProductVO> productVOList = new ArrayList<ProductVO>();
        for (Product product : productList){
            ProductVO productVO = new ProductVO();
            BeanUtils.copyProperties(product,productVO);

            QueryWrapper wrapper = new QueryWrapper();
            wrapper.eq("id",product.getCategoryleveloneId());
            ProductCategory productCategory = productCategoryMapper.selectOne(wrapper);
            if (productCategory != null){
                productVO.setCategorylevelone(productCategory.getName());
            }

            wrapper = new QueryWrapper();
            wrapper.eq("id",product.getCategoryleveltwoId());
            if (productCategory != null){
                productVO.setCategoryleveltwo(productCategory.getName());
            }

            wrapper = new QueryWrapper();
            wrapper.eq("id",product.getCategorylevelthreeId());
            if (productCategory != null){
                productVO.setCategoryleveltwo(productCategory.getName());
            }
            productVOList.add(productVO);
        }
        dataVO.setData(productList);
        return dataVO;
    }
}
