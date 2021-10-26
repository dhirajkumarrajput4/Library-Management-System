package com.niit.booklibrary.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;
import javax.validation.Valid;

import org.apache.tomcat.util.codec.binary.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import com.niit.booklibrary.dao.BookDAO;
import com.niit.booklibrary.dao.StudentDAO;
import com.niit.booklibrary.dao.Userdao;
import com.niit.booklibrary.model.Book;
import com.niit.booklibrary.model.Student;
import com.niit.booklibrary.model.User;

@Controller
public class MyController {
	@Autowired
	Userdao userdao;

	@Autowired
	BookDAO bookdao;

	@RequestMapping("/login")
	public String login() {
		/*
		 * User user=null; model.addAttribute("user", user);
		 */
		return "login";
	}

	@RequestMapping("/register")
	public String register(Model model) {
		return "register";
	}

	@ModelAttribute
	public User createUser() {
		return new User();
	}

	@RequestMapping(value = "/registerform", method = RequestMethod.POST)
	public String showRegister(@Valid @ModelAttribute("user") User user, BindingResult result,
			HttpServletRequest request) throws IOException {
		if (result.hasErrors()) {
			return "register";
		} else {
			System.out.println(user.getFirstName());
			this.userdao.save(user);

			return "redirect:/";
		}
	}

	@RequestMapping(value = "/loginform", method = RequestMethod.POST)
	public String checkLogin(@RequestParam("email") String Email, @RequestParam("password") String Password,
			Model model, HttpServletRequest request) {
		System.out.println(Email);
		System.out.println(Password);
		User checkUser = userdao.checkUser(Email, Password);
		if (checkUser == null) {
			request.getSession().setAttribute("msg", "Invailid Username or Password");
			request.getSession().setAttribute("clss", "alert-danger");
			return "redirect:/login";

		} else {
			if (checkUser.getUserType().equals("admin")) {
				// retrieve book on home page
				List<Book> books = bookdao.getBooks();
				request.getSession().setAttribute("logedUser", checkUser);
				request.getSession().setAttribute("name", checkUser.getFirstName());
				request.getSession().setAttribute("usertype", checkUser.getUserType());
				request.getSession().removeAttribute("msg");
				request.getSession().removeAttribute("clss");
				return "redirect:/";
			}

			else {
				// retrieve book on home page
				List<Book> books = bookdao.getBooks();
				request.getSession().setAttribute("logedUser", checkUser);
				request.getSession().setAttribute("name", checkUser.getFirstName());
				request.getSession().removeAttribute("msg");
				request.getSession().removeAttribute("clss");
				return "redirect:/";
			}
		}
	}

	@RequestMapping("/logout")
	public String logout(Model model, HttpSession session, HttpServletRequest request) {
		// return all books on home page
		/*
		 * List<Book> books = bookdao.getBooks(); model.addAttribute("books", books);
		 */

		request.getSession().removeAttribute("logedUser");
		request.getSession().removeAttribute("name");
		request.getSession().removeAttribute("usertype");
		System.out.println(request.getServletPath());
		return "redirect:/";
	}

	/* book model attribute */
	@ModelAttribute
	public Book createBook() {
		return new Book();
	}

	@RequestMapping(value = "/registerbookform", method = RequestMethod.POST)
	public String showBooks(@Valid @ModelAttribute("book") Book book, BindingResult result, HttpServletRequest request,
			Model m) throws IOException {
		boolean error = false;
		if (result.hasErrors()) {
			error = true;
			m.addAttribute("error", error);
//			retrieve all books on home page
			List<Book> books = bookdao.getBooks();
			m.addAttribute("books", books);
			return "index";
		} else {
			System.out.println(book.getBooktitle());
			this.bookdao.save(book);
			return "redirect:/";
		}
	}

	@RequestMapping(value = "/")
	public String getAllBook(Model model, HttpSession session) {
		User user = null;
		model.addAttribute("user", user);
		List<Book> books = bookdao.getBooks();
//		model.addAttribute("book1",new Book());
		model.addAttribute("books", books);

		session.removeAttribute("msg");
		session.removeAttribute("clss");

		return "index";
	}

	@RequestMapping("/deletebook/{id}")
	public ModelAndView deleteBook(@PathVariable("id") int id) {
		this.bookdao.deleteBook(id);
		ModelAndView mv = new ModelAndView("redirect:/");
		return mv;
	}

	@RequestMapping("/updatebook/{id}")
	public String updateBook(@PathVariable("id") int id, Model model) {
		System.out.println("this is update controller");

		// get all books on home page and /bookdelete/url
		List<Book> books = bookdao.getBooks();
		model.addAttribute("books", books);

		Book bookById = this.bookdao.getBookById(id);
		model.addAttribute("book", bookById);
		System.out.println(bookById.getBookid());
		return "index";
	}

	@RequestMapping("/search")
	public String searchBook(@RequestParam("searchType") String SearchType,
			@RequestParam("searchValue") String SearchValue, Model model) {
		if (SearchType.equals("bname")) {
			List<Book> book = this.bookdao.getBooksByBookName(SearchValue);
			model.addAttribute("books", book);
		} else {
			List<Book> book = this.bookdao.getBooksByAuthorName(SearchValue);
			model.addAttribute("books", book);
		}
		return "SearchedBooks";
	}

	@RequestMapping("/users")
	public String getAllUsers(Model model) {
		List<User> Users = userdao.getAllUsers();
		model.addAttribute("Users", Users);

		return "viewUser";
	}

//	get user details handler
	@RequestMapping(value = "/userdetails/{id}")
	public ModelAndView getUserById(@PathVariable(name = "id") Integer uid, Model model) {
		System.out.println("inside userdetails handler   " + uid);
//		int Id=Integer.parseInt(uid);
		User myuser = userdao.getUserById(uid);
		System.out.println(myuser.getFirstName());
//		model.addAttribute("myuser", userdao.getUserById(Id));
		ModelAndView mv = new ModelAndView("userDetails");
		mv.addObject("user", myuser);
		return mv;
	}

//delete user handler
	@RequestMapping(value = "/userdelete/{id}")
	public String deleteUser(@PathVariable(name = "id") Integer id) {
		userdao.deleteuser(id);

		return "redirect:/users";
	}

//	update User handler
	@RequestMapping(value = "/userupdate/{id}")
	public String updateUser(@PathVariable(name = "id") Integer id, Model model) {
		User user = userdao.getUserById(id);
		model.addAttribute("user", user);
		return "register";
	}

	@ModelAttribute
	public Student addStudent() {
		return new Student();
	}
	// Student handler

	@Autowired
	StudentDAO studentdao;

	@RequestMapping(value = "/registerstudentform", method = RequestMethod.POST)
	public String showBooks(@Valid @ModelAttribute("student") Student student, BindingResult result,
			HttpServletRequest request, Model m) throws IOException {
		boolean error = false;
		if (result.hasErrors()) {
			error = true;
			m.addAttribute("error", error);

			return "index";
		} else {

			this.studentdao.save(student);

			return "redirect:/";
		}
	}

	@RequestMapping("/updatestudent/{id}")
	public String updateStudent(@PathVariable("id") int id, Model model) {
		System.out.println("this is update controller");

		Student studentById = this.studentdao.getStudentById(id);
		model.addAttribute("student", studentById);

		return "index";
	}

	// Book issue handler
	@RequestMapping("/issuebook/{id}")
	public String provideBook(@PathVariable("id") int id, Model model) {
		Book bookById = bookdao.getBookById(id);
		model.addAttribute("book", bookById);
		return "issuebooks";
	}
	
	@RequestMapping("/getStudent")
	public String student(@RequestParam("studentid")int sid,@RequestParam("bookid")int bid ,Model model)
	{
		Student studentById = studentdao.getStudentById(sid);
		model.addAttribute("student",studentById);
		Book bookById=this.bookdao.getBookById(bid);
		model.addAttribute("book",bookById);
		return "issuebooks";
	}
	
	@RequestMapping("/issue")
	public String bookissue(@RequestParam("studentid")int sid,@RequestParam("bookid")int bid ,Model model)
	{
		Student studentById = studentdao.getStudentById(sid);
		model.addAttribute("student",studentById);
		Book bookById=this.bookdao.getBookById(bid);
		model.addAttribute("book",bookById);
		return "issuebooks";
	}
}

