package cn.ncu.edu.onlineshopmall.Controller.admin;

import cn.ncu.edu.onlineshopmall.Service.CommodityService;
import cn.ncu.edu.onlineshopmall.Service.ShopService;
import cn.ncu.edu.onlineshopmall.entity.Commodity;
import cn.ncu.edu.onlineshopmall.entity.ImagePath;
import cn.ncu.edu.onlineshopmall.entity.Shop;
import cn.ncu.edu.onlineshopmall.entity.User;
import cn.ncu.edu.onlineshopmall.util.ImageUtil;
import cn.ncu.edu.onlineshopmall.util.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;


@Controller
@RequestMapping("/goods")
public class CommpdityController {

    @Autowired
    private CommodityService  commodityService;

    @Autowired
    private ShopService shopService;

    @RequestMapping("searchAllGoods")
    public String SerchAll(Model model, HttpSession Session){
        User user=(User)Session.getAttribute("admin");
        if(user.getRole()==3)
            model.addAttribute("GoodsList",commodityService.findAllCommodity());
        else  if (user.getRole()==2){
            Shop shop=shopService.findShopByUsername(user.getUsername());
            model.addAttribute("MyGoodList", commodityService.findAllCommdityByShopid(shop.getShopid()));
        }
        return "goods-list";
    }
    @RequestMapping(value = "deleteGoodsById",method = RequestMethod.GET)
    public String deleteById(@RequestParam("commodityid")String commodityid) {
        commodityService.deleteCommodityById(commodityid);
        return "redirect:/goods/searchAllGoods";
    }

    @RequestMapping("/add")
    public String addgoods(@ModelAttribute("successMsg")String msg,Model model){
        //若添加商品成功，则接收/addSuccess传过来的消息并传给add-list页面
        if(!msg.equals("")){
            model.addAttribute("msg",msg);
        }

        return "add-goods";
    }

    @RequestMapping("/addGoodsSuccess")
    public String addGoods(Commodity goods, @RequestParam MultipartFile[] fileToUpload, RedirectAttributes redirectAttributes){

        goods.setCommodityid(UUIDUtils.getUUID());
        commodityService.addGoods(goods);
        for (MultipartFile multipartFile : fileToUpload){
            /**
             * 对文件名进行操作防止文件重名
             */
            //获取原始文件名，并加工处理得到新的文件名
            //String fileName = goods.getCommodityid()+multipartFile.getOriginalFilename();
            if(multipartFile != null){
                //进一步处理文件名，并将文件保存到本地目录中
                String ImagePath = ImageUtil.imagePath(multipartFile,goods.getCommodityid());
                 System.out.println("最后存入数据库的图片名字为："+ImagePath);
            }
        }
        redirectAttributes.addFlashAttribute("successMsg","商品添加成功！");
        return "redirect:/goods/add";
    }

}
