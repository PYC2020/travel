package com.yc.admin.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yc.admin.MD5Utils;
import com.yc.admin.dao.Impl.adminMapper;
import com.yc.admin.domain.AdminDomain;
import com.yc.admin.domain.PageDomain;
import com.yc.admin.entity.admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {
    @Autowired(required = false)//手动设置false，bean不存在时注入不会失败
    private adminMapper am;
    @Transactional(readOnly = true)//在将事务设置成只读后，相当于将数据库设置成只读数据库，此时若要进行写的操作，会出现错误
    @Override
    public List<AdminDomain> list(){
        List<admin>list=am.selectAll();
        List<AdminDomain> r = new ArrayList<>();
        for (admin a : list) {   //  BeanUtils.copyBean(source, destination);
            AdminDomain ad = new AdminDomain(a.getUid(), a.getUname(), a.getPwd(),a.getTel());
            r.add(ad);
        }
        return r;
    }

    @Transactional(readOnly = true)
    @Override
    public PageDomain<AdminDomain> listByPage(AdminDomain adminDomain) {
        Example example = new Example(admin.class);   //条件
        //分页条件设置
        PageHelper.startPage(adminDomain.getPage(), adminDomain.getPageSize());
        //排序条件
        example.setOrderByClause("uid desc");
        //  Criteria: 查询的规则
        Example.Criteria c = example.createCriteria();
//        if (CommonUtils.isNotNull(picDomain.getPicpath())) {
//            //条件创建    where 1=1 and description like '%xx%';
//            c.andLike("picpath", "%" + picDomain.getPicpath() + "%");
//        }
        // PageInfo: 分页的结果   总记录数，第几页，每页多少条条，上一页，下一页， 总共多少页.
        PageInfo<admin> pageInfo = new PageInfo<admin>(am.selectByExample(example));


        PageDomain<AdminDomain> pageDomain = new PageDomain<AdminDomain>();
        pageDomain.setTotal(pageInfo.getTotal());
        pageDomain.setPage(pageInfo.getPageNum());
        pageDomain.setPageSize(adminDomain.getPageSize());
        //List<Pic> list = picMapper.selectByExample(example);
        List<AdminDomain> r = new ArrayList<AdminDomain>();
        //从pageInfo中取记录数
        if (pageInfo.getList() != null) {
            for (admin a : pageInfo.getList()) {
                AdminDomain ad = new AdminDomain(a.getUid(), a.getUname(), a.getPwd(),a.getTel());
                r.add(ad);
            }
        }
        pageDomain.setData(r);
        return pageDomain;
    }

    @Override
    public void save(AdminDomain adminDomain) {
        admin a = new admin();
        a.setUname(adminDomain.getUname());
        adminDomain.setPwd(MD5Utils.stringToMD5(adminDomain.getPwd()));
        a.setPwd(adminDomain.getPwd());
        a.setTel(adminDomain.getTel());
        this.am.insert(a);
        adminDomain.setUid(a.getUid());
    }

    @Override
    public void delete(Integer id) {
        this.am.deleteByPrimaryKey(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List findOne(AdminDomain adminDomain) {
        admin a  = new admin();
        //mybatis的逆向工程中会生成实例及实例对应的example，example用于添加条件，相当where后面的部分
        Example example = new Example(admin.class);
        Example.Criteria criteria = example.createCriteria();
        adminDomain.setPwd(MD5Utils.stringToMD5(adminDomain.getPwd()));
        criteria.andEqualTo("uname", adminDomain.getUname());
        criteria.andEqualTo("pwd", adminDomain.getPwd());
        List<admin>list=  am.selectByExample(example);
        return list;
    }
    //login
    @Transactional(readOnly = true)
    @Override
    public AdminDomain login(AdminDomain adminDomain) {
        AdminDomain ad=new AdminDomain();
        Example example = new Example(admin.class);
        Example.Criteria criteria = example.createCriteria();
        adminDomain.setPwd(MD5Utils.stringToMD5(adminDomain.getPwd()));
        criteria.andEqualTo("uname", adminDomain.getUname());
        criteria.andEqualTo("pwd", adminDomain.getPwd());
        List<admin>list=  am.selectByExample(example);
        for (admin a : list) {
            ad = new AdminDomain(a.getUid(), a.getUname(), a.getPwd(),a.getTel());
        }
        if(list!=null){
        }
        return ad;
    }
}
