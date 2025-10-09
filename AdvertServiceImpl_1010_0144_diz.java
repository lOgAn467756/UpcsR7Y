// 代码生成时间: 2025-10-10 01:44:24
package com.example.adservice.service.impl;

import com.example.adservice.dao.AdvertMapper;
import com.example.adservice.domain.Advert;
import com.example.adservice.service.AdvertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdvertServiceImpl implements AdvertService {

    @Autowired
    private AdvertMapper advertMapper;

    /**
     * 添加广告
     *
     * @param advert 广告对象
     * @return 返回添加结果
     */

    @Override
    @Transactional
    public int addAdvert(Advert advert) {
        return advertMapper.insertAdvert(advert);
    }

    /**
     * 更新广告
     *
     * @param advert 广告对象
     * @return 返回更新结果
     */

    @Override
    @Transactional
    public int updateAdvert(Advert advert) {
        return advertMapper.updateAdvert(advert);
    }

    /**
     * 删除广告
     *
     * @param advertId 广告ID
     * @return 返回删除结果
     */

    @Override
    @Transactional
    public int deleteAdvert(int advertId) {
        return advertMapper.deleteAdvert(advertId);
    }

    /**
     * 获取所有广告
     *
     * @return 返回广告列表
     */

    @Override
    public List<Advert> getAllAdverts() {
        return advertMapper.selectAllAdverts();
    }

    /**
     * 根据ID获取广告
     *
     * @param advertId 广告ID
     * @return 返回广告对象
     */

    @Override
    public Advert getAdvertById(int advertId) {
        return advertMapper.selectAdvertById(advertId);
    }
}
