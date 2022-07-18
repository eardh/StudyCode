package com.dahuang.controller;

import com.dahuang.pojo.Books;
import com.dahuang.service.BookService;
import com.sun.xml.internal.ws.resources.WsdlmodelMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {
    //controller 调 service 层
    @Autowired
    @Qualifier("BookServiceImpl")
    private BookService bookService;

    //查询全部的书籍，并且返回到一个书籍展示页面
    @RequestMapping("/allBook")
    public String list(Model model){

        List<Books> books = bookService.queryAllBook();
        model.addAttribute("list",books);
        return "allBook";
    }

    //跳转到增加书籍页面
    @RequestMapping("/toAdd")
    public String toAddPaper(){
        return "addBook";
    }

    //添加图书请求
    @RequestMapping("/addBook")
    public String addBook(Books books){
        System.out.println(books);
        bookService.addBook(books);
        return "redirect:/book/allBook";
    }

    //跳转到修改书籍页面
    @RequestMapping("/toUpdate")
    public  String toUpdatePaper(int id,Model model){
        Books book = bookService.queryBookById(id);
        model.addAttribute("book",book);
        return "updateBook";
    }

    //修改书籍
    @RequestMapping("/updateBook")
    public String updateBook(Books books){
        System.out.println(books);
        bookService.updateBook(books);
        return "redirect:/book/allBook";
    }

    //删除书籍
    @RequestMapping("/deleteBook/{bookId}")
    public String deleteBook(@PathVariable("bookId") int id){
        bookService.deleteBookById(id);
        return "redirect:/book/allBook";
    }

    //查询书籍
    @RequestMapping("/queryBook")
    public String queryBook(String bookName,Model model){
        List<Books> books = bookService.queryBookByName(bookName);
        if(books.isEmpty())
        {
            model.addAttribute("error","未查到书籍");
        }
        model.addAttribute("list",books);
        return "allBook";
    }
}
