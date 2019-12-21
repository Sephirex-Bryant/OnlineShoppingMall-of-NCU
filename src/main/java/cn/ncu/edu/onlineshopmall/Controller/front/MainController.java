package cn.ncu.edu.onlineshopmall.Controller.front;

import cn.ncu.edu.onlineshopmall.Service.CommodityService;
import cn.ncu.edu.onlineshopmall.entity.Commodity;
import cn.ncu.edu.onlineshopmall.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private CommodityService goodsService;

    //主页面部分商品展示
    @RequestMapping("/main")
    public String showAllGoods(Model model, HttpServletRequest request) {


        List<Commodity> goodsList = goodsService.findAllCommodity();
        User user = (User) request.getSession().getAttribute("LoginUser");
        //User user = (User) request.getSession().getAttribute("user");


        //轮播图展示3个商品：1个随机活动商品，一个最新上架商品，一个销量最高商品
//        Random rand = new Random();
//        int max = goodsList.size();
//        List<Goods> shufList = new ArrayList<>();
//        shufList.add(goodsList.get(rand.nextInt(max)));
//        shufList.add(recentGoods.get(0));
//        shufList.add(hotGoods.get(0));
//        model.addAttribute("shufList",setGoodsListCollection(shufList,user));
//
//
//        model.addAttribute("categoryList",categoryList);
//        model.addAttribute("goodsList",setGoodsListCollection(goodsList,user));
//        model.addAttribute("recentGoods",setGoodsListCollection(recentGoods,user));
//        model.addAttribute("hotGoods",setGoodsListCollection(hotGoods,user));

        return "index";
    }

    //按关键字模糊查询商品
    @RequestMapping("/search")
    public String searchGoods(Model model, String goodsName, Integer pageSize, Integer pageNum, Integer pageIndex, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (pageSize == null) {
            pageSize = 6;
        }
        if (pageNum == null) {
            pageNum = 1;
        }
        if (pageIndex == null) {
            pageIndex = 1;
        }

//        List<Goods> goodsList = goodsService.mySelectByName(goodsName,pageSize,pageNum);
//        int total = (int) ((Page) goodsList).getTotal();
//        int num = total%pageSize == 0? total/pageSize:total/pageSize+1;
//        int page[] = new int[num];
//        for(int i =0;i<num;i++){
//            page[i]=i+1;
//        }
//        List<Category> categoryList=cateService.listCategoryAndGoodNumber();
//        model.addAttribute("categoryList",categoryList);
//        model.addAttribute("goodsList",setGoodsListCollection(goodsList,user));
//        model.addAttribute("pages",page);
//        model.addAttribute("pageSize",pageSize);
//        model.addAttribute("pageIndex",pageIndex);
        return "deals_grid";
    }

    //展示所有商品
    @RequestMapping("/jump")
    public String junmp(Model model, Integer pageSize, Integer pageNum, Integer pageIndex, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (pageSize == null) {
            pageSize = 6;
        }
        if (pageNum == null) {
            pageNum = 1;
        }
        if (pageIndex == null) {
            pageIndex = 1;
        }


//        List<Goods> goodsList = goodsService.findAllProducts(pageSize,pageNum);
//        int total = (int) ((Page) goodsList).getTotal();
//        int num = total%pageSize == 0? total/pageSize:total/pageSize+1;
//        int page[] = new int[num];
//        for(int i =0;i<num;i++){
//            page[i]=i+1;
//        }
//        List<Category> categoryList=cateService.listCategoryAndGoodNumber();
//        model.addAttribute("categoryList",categoryList);
//        model.addAttribute("goodsList",setGoodsListCollection(goodsList,user));
//        model.addAttribute("pages",page);
//        model.addAttribute("pageSize",pageSize);
//        model.addAttribute("pageIndex",pageIndex);
        return "deals_grid";
    }


    //------------------------------------------------------------------------------------
    //分类查询
    @RequestMapping("/showCateGoodsByLowPrice")
    public String showCateGoodsByLowPrice(Integer categoryId, Model model, Integer pageNum, Integer pageSize, Integer pageIndex) {
        if (pageSize == null) {
            pageSize = 6;
        }
        if (pageNum == null) {
            pageNum = 1;
        }
        if (pageIndex == null) {
            pageIndex = 1;
        }

//        List<Goods> goodsList = goodsService.getByCategoryIdLow(categoryId,pageNum,pageSize);
//        int total = (int) ((Page) goodsList).getTotal();
//        int num = total%pageSize == 0? total/pageSize:total/pageSize+1;
//        int pages[] = new int[num];
//        for(int i =0;i<num;i++){
//            pages[i]=i+1;
//        }
//
//        List<Category> categoryList=cateService.listCategoryAndGoodNumber();
//        model.addAttribute("categoryList",categoryList);
//        model.addAttribute("categoryId",categoryId);
//        model.addAttribute("goodsList",goodsList);
//        model.addAttribute("pages",pages);
//        model.addAttribute("pageSize",pageSize);
//        model.addAttribute("pageIndex",pageIndex);
        return "goods-by-category";
    }

}
