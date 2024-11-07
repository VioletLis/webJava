package ru.lisenkova;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class BookController {
    @Autowired
    private BookService bookService;
    @Autowired
    ReaderService readerService;
    @Autowired
    BookRepository repo;
    @Autowired
    ReaderRepository repoReaders;
    @GetMapping("/")
    public String handle(Model model){
        List<Book> listBooks = repo.findAll();
        model.addAttribute("listBooks",listBooks);
        return "index";

    }
    @GetMapping("/new_book")
    public String handleNewBook(Model model){
        Book book = new Book();
        model.addAttribute("book", book);
        return "new_book";

    }

    @RequestMapping("/searchBook")
    public ModelAndView search(@RequestParam String keyword) {
        List<Book> result = repo.search(keyword);
        ModelAndView mav = new ModelAndView("searchBook");
        mav.addObject("result", result);
        return mav;
    }
    @GetMapping("/deleteBook/")
    public String handleDelete(@RequestParam Long isbn,Model model){
        Reader reader=readerService.get(bookService.get(isbn).getReader().getId());
        reader.returnBorrowedBooks(bookService.get(isbn));
        readerService.update(reader);
        bookService.delete(isbn);
        List<Book> listBooks = repo.findAll();
        model.addAttribute("listBooks",listBooks);
        return "redirect:"+"/";
    }
    @RequestMapping(value = "/saveBook", method = RequestMethod.POST)
    public String create(@ModelAttribute("book") Book book, Model model, BindingResult result) {
        repo.save(book);
       // List<Book> listBooks = repo.findAll();
    //    model.addAttribute("listBooks",listBooks);
        if (result.hasErrors()) {
            System.out.println("error in result");
            return "/new_book";
        }
        return "redirect:/";
    }
    @GetMapping("/lend/")
    public String handleLend(@RequestParam Long isbn, Model model){
        Book book = bookService.get(isbn);
        List<Reader> listReaders = repoReaders.findAll();
        if(listReaders.size()==0)
            return "readers";
        model.addAttribute("book",book);
        model.addAttribute("listReaders",listReaders);
        return "lend_book";

    }
    @RequestMapping(value = "/lendBook", method = RequestMethod.POST)
    public String lend(@ModelAttribute("book") Book book, @RequestParam(name="idReader") Integer id, Model model, BindingResult result) {
        book.setReader(repoReaders.findById(id).get());
        bookService.update(book);
        repoReaders.findById(id).get().addBorrowedBooks(book);
        readerService.update(repoReaders.findById(id).get());
        if (result.hasErrors()) {
            System.out.println("error in result");
        }
        return "redirect:/";
    }
    @GetMapping("/getBack/")
    public String handleGetBack(@RequestParam Long isbn, Model model){
        Book book = bookService.get(isbn);
        Reader reader=readerService.get(book.getReader().getId());
        reader.returnBorrowedBooks(book);
        readerService.update(reader);
        book.setReader(null);
        bookService.update(book);
        return "redirect:/";

    }
}
