package com.example.demo;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MyController {
	
	@Autowired
	MyService service;
	PersonRepository personRepository;
	
	//indexとバックスラッシュ(/)は同じ意味!!
	@GetMapping("/")
	public String getIndex() {
		return "index";
	}
	
	@GetMapping("/real")
	public String getReal() {
		return "real";
	}
	
	@GetMapping("/test")
	public String getTest(Model model) {
		
		//文字の色：JavaScript
		model.addAttribute("greeting", "ようこそ!! 深海へようこそ");
		
		model.addAttribute("headerColor", "blue");
		
		Person p = new Person();
		p.setName("帰溟スペクター");
		p.setAge(22);

		model.addAttribute("person", p);
		
		return "test";
	}
	
	@GetMapping("/users/{name}")
	public String getUsers(@PathVariable String name, Model model) {
		System.out.println("name = " + name);
		model.addAttribute("message", name + "さん、こんにちは！");
		return "real";
	}
	
	@GetMapping("/sample1_input")
	public String Sample1_Input(Model model) {
		model.addAttribute("title", "入力画面1です。");
		return "sample1_input";
	}
	
	@GetMapping("/sample1_result")
	public String Sample1_result(@RequestParam String paramA, @RequestParam String paramB, @RequestParam String paramC, Model model) {
	    model.addAttribute("paramA", paramA);
	    model.addAttribute("paramB", paramB);
	    model.addAttribute("paramC", paramC);
	    return "sample1_result";
	}
	
	@GetMapping("/sample2_input")
	public String sample2_input(Model model) {
		model.addAttribute("title", "入力画面2です。");
		return "sample2_input";
	}

	@PostMapping("/sample2_result")
	public String sample2_result(ThreeTextForm form, Model model) {
		System.out.println("paramA : " + form.getParamA());
		System.out.println("paramB : " + form.getParamB());
		System.out.println("paramC : " + form.getParamC());
	    model.addAttribute("form", form);
	    return "sample2_result";
	}
	
	@GetMapping("/sample3_input")
	public String sample3_input(Model model) {				
		model.addAttribute("title", "入力画面3です。");
	    model.addAttribute("form", new ThreeTextForm_Validated());        
		return "sample3_input";
	}

	@PostMapping("/sample3_result")
	public String sample3_result(@Validated @ModelAttribute("form") ThreeTextForm_Validated form, BindingResult bindingResult, Model model) {

		System.out.println("paramA : " + form.getParamA());
		System.out.println("paramB : " + form.getParamB());
		System.out.println("paramC : " + form.getParamC());

		if(bindingResult.hasErrors()) {
			model.addAttribute("title", "入力画面3の再入力です。");
	        model.addAttribute("form", form);        
			return "sample3_input";
		}
		
	    model.addAttribute("form", form);
	    
	    return "sample3_result";
	}
	
	@GetMapping("/personList")
    public String personList(Model model) {
		model.addAttribute("persons",service.getPersonList());
	    return "personList";
	    
	}
	
	private final JpaDao jpadao;
	
	public MyController(JpaDao jpadao) {
		this.jpadao = jpadao;
	}
	
	@RequestMapping("/person")
	public String index(Model model) {
		model.addAttribute("personslist", jpadao.findAll());
		return "busyo_list";
	}
	
	@GetMapping("/busyo_input")
	public String form(Model model, Form form) {
		return "busyo_input";
	}
	
	@GetMapping("/confirm")
	public String confirm(Model model, @Validated Form form, BindingResult result) {
		//エラーがあれば入力画面に戻す
		if(result.hasErrors()) {
			return "busyo_input";
		}
		return "busyo_confirm";
	}

	//【JPAでINSERT】
	//完了画面・登録(INSERT)
	@GetMapping ("/complete")
	public String complete(Form form, Model model){
		//フォームの値をエンティティに入れ直す
		Person s1 = new Person();
		s1.setName(form.getName());
		s1.setAge(form.getAge());
		s1.setAddress(form.getAddress());
		//JPAでINSERT実行
		jpadao.save(s1);
		return "busyo_complete";
	}
	
	@GetMapping("/del/{id}")
	public String destory(@PathVariable int id) {
	
		// JPAの削除用のメソッドを使って実行
		System.out.println("id="+id);
		jpadao.deleteById(id);
		return "redirect:/person";
	}
//	
	@GetMapping("/edit/{id}")
	public String editView(@PathVariable int id, Model model) {
		// 値がnullの場合も許可するOptionalで取得
		Optional<Person> opt1 = jpadao.findById(id);
		Person s1 = opt1.get();
		System.out.println(s1.getId());
		System.out.println(s1.getName());
		System.out.println(s1.getAge());
		System.out.println(s1.getAddress());
		model.addAttribute("person", s1);
		return "busyo_edit";
	}

	//【JPAでUPDATE2：更新処理(UPDATE)】
	@GetMapping("/busyo_edit/exe/{id}")
	public String editExe(@PathVariable int id, Form form, Model model) {

		System.out.println(form.getName());//取得できているかの確認

		//フォームの値をエンティティに入れ直し
		Person s1 = new Person();
		s1.setName(form.getName());
		s1.setAge(form.getAge());
		s1.setAddress(form.getAddress());
		s1.setId(id);

		jpadao.updateDb(s1.getName(),s1.getAge(),s1.getAddress(),id);
		jpadao.save(s1);

		return "redirect:/person";
	}
	
	
	@PostMapping("/person_input/{id}")
	 public String getbusyo_history(@PathVariable int id, Model model) {
		Person person = new Person();
		person.setId(id); // idをセット
		model.addAttribute("id", id);
		model.addAttribute("profile", person);
        return "myprofile_input"; // assuming the name of your input form is busyo_history_input_form.html
    }
	
	@PostMapping("/busyo_story/{id}")
	public String postbusyo_history(@PathVariable int id, @ModelAttribute Person person, Model model) {
		
		ChatGptApiClient client = new ChatGptApiClient();
		try {
			Optional<Person> opt1 = jpadao.findById(id);
			Person s1 = opt1.get();
			String responseMessage = client.callChatGptApi(s1,person);
			model.addAttribute("history", responseMessage);
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}	
		return "busyo_history";
	}
	
//	private String callChatGptApi(Person person) {
//        // Implementation of API call to ChatGPT
//        // Return generated story
//        return "Generated story for " + person.getName();
//    }
	
	@PostMapping("/busyo_question")
	public String postbusyo_history(String question, Model model) {
		
		ChatGptApiClient client = new ChatGptApiClient();
		try {
//			Optional<Person> opt1 = jpadao.findById(id);
//			Person s1 = opt1.get();
			String responseMessage = client.questionChatGptApi(question);
			model.addAttribute("question", responseMessage);
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}	
		return "busyo_question";
	}
	
	@PostMapping("/busyo_castle")
	public String postbusyo_castle(String castle, Model model) {
		
		ChatGptApiClient client = new ChatGptApiClient();
		try {
//			Optional<Person> opt1 = jpadao.findById(id);
//			Person s1 = opt1.get();
			String responseMessage = client.castleChatGptApi(castle);
			model.addAttribute("castle", responseMessage);
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}	
		return "busyo_castle";
	}
	
	@GetMapping("/login")
	public String login(Model model) {
		return "login";
	}
	
	@GetMapping("/busyosearch_input")
	public String busyosearch_input(Model model) {
		model.addAttribute("title", "キーワードを入力してね!!");
		return "busyosearch_input";
	}
	
	@GetMapping("/busyosearch_result")
	public String busyosearch_result(@RequestParam String keyword, Model model) {
		model.addAttribute("title", "検索結果です!!");
		model.addAttribute("persons",service.getPersonList(keyword));
		return "busyosearch_result";
	}
	
}
