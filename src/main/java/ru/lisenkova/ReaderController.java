package ru.lisenkova;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ReaderController {
    @Autowired
    private BookService bookService;
    @Autowired
    private ReaderService readerService;
    @Autowired
    private ReaderRepository repoReader;
    @GetMapping("/readers")
    public String handle(Model model){
        List<Reader> listReaders = repoReader.findAll();
        model.addAttribute("listReaders",listReaders);
        return "readers";
    }
    @GetMapping("/new_reader")
    public String handleNewReader(Model model){
        Reader newReader = new Reader();
        model.addAttribute("reader",newReader);
        return "new_reader";
    }

    @RequestMapping("/editReader/")
    public ModelAndView editReaderForm(@RequestParam Integer id) {
        ModelAndView mav = new ModelAndView("edit_reader");
        Reader reader = readerService.get(id);
        readerService.update(reader);
        mav.addObject("reader", reader);

        return mav;
    }
    @RequestMapping(value = "/saveReader", method=RequestMethod.POST)
    public String saveReader(@ModelAttribute("reader") Reader reader) {
        System.out.println(reader.getId());
        System.out.println(reader.getName());
        System.out.println(reader.getSurname());
        repoReader.deleteById(reader.getId());
        repoReader.save(reader);
        return "redirect:/readers";
    }
    @RequestMapping(value = "/updateReader", method=RequestMethod.POST)
    public String editReader(@ModelAttribute("reader") Reader reader) {
        System.out.println(reader.getId());
        System.out.println(reader.getName());
        System.out.println(reader.getSurname());
        readerService.update(reader);
        return "redirect:/readers";
    }
    @RequestMapping("/searchReader")
    public ModelAndView search(@RequestParam String keyword) {
        List<Reader> result = repoReader.search(keyword);
        ModelAndView mav = new ModelAndView("searchReader");
        mav.addObject("result", result);
        return mav;
    }
    @GetMapping("/deleteReader/")
    public String handleDelete(@RequestParam Integer id, Model model){
        Reader reader=readerService.get(id);
        List<Book> books=reader.getBorrowedBooks();
        if (books.size()!=0)
            for (Book book:books) {
                book.setReader(null);
                bookService.update(book);
            }
        reader.setBorrowedBooks(null);
        readerService.update(reader);
        readerService.delete(id);
        List<Reader> listReaders = repoReader.findAll();
        model.addAttribute("listReaders",listReaders);
        return "redirect:"+"/readers";
    }

}
