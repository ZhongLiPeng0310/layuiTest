package com.lbxtest.layui.Service;

import com.lbxtest.layui.vo.DataVO;
import com.lbxtest.layui.vo.ProductVO;

public interface ProductService {
    public DataVO<ProductVO> findData(Integer page,Integer limit);
}
