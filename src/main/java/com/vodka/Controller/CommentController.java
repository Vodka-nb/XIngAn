package com.vodka.Controller;
import com.vodka.Service.ProcessComment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@ResponseBody
public class CommentController {

    private ProcessComment processComment = new ProcessComment();

    //获取所有评论
    @RequestMapping(value = "/GetCommentContent",produces="text/html;charset=utf-8",method ={RequestMethod.GET ,RequestMethod.POST })
    public String GetCommentInfo(HttpServletRequest request, HttpSession session){
        return processComment.GetComment(request,session);
    }

    //编辑某条评论
    @RequestMapping(value = "/EditCommentContent",method ={ RequestMethod.GET , RequestMethod.POST })
    public Integer EditCommentInfo(HttpServletRequest request, HttpServletResponse response){
        return  processComment.EditComment(request,response);
    }

    //删除某条评论
    @RequestMapping(value = "/DeleteComment",method={RequestMethod.GET,RequestMethod.POST})
    public  Integer DeleteCommentInfo(HttpServletRequest request, HttpServletResponse response){
        return processComment.DeleteComment(request,response);
    }

    //处理用户新的评论
    @RequestMapping(value = "/CustomerRemark",method={RequestMethod.GET,RequestMethod.POST})
    public  Integer AddCommentInfo(HttpServletRequest request, HttpServletResponse response){
        return processComment.AddComment(request,response);
    }

    //管理员获取评论
    @RequestMapping(value = "/AuditCommentTable",produces="application/json;charset=utf-8",method ={RequestMethod.GET ,RequestMethod.POST })
    public String AuditCommentInfo(HttpServletRequest request, HttpServletResponse response) {
        return processComment.AuditComment(request,response);
    }

    //管理员审核评论
    @RequestMapping(value = "/ThroughComment",produces="application/json;charset=utf-8",method ={RequestMethod.GET ,RequestMethod.POST })
    public Integer ThroughCommentInfo(HttpServletRequest request, HttpServletResponse response) {
        return processComment.ThroughComment(request,response);
    }



}
