package br.com.gasfacil.controller;
import br.com.gasfacil.pattern.DAOFactory;
import br.com.gasfacil.pattern.strategy.RegraBeneficio;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.math.BigDecimal;
import java.sql.Date;
@Controller
public class WebController {
 private final DAOFactory factory; private final RegraBeneficio regra;
 public WebController(DAOFactory factory, RegraBeneficio regra){this.factory=factory;this.regra=regra;}
 @GetMapping("/") public String inicio(Model m){try{m.addAttribute("totalBeneficiarios",factory.beneficiarios().listar().size());m.addAttribute("totalBotijoes",factory.botijoes().listar().size());m.addAttribute("totalRetiradas",factory.retiradas().listar().size());m.addAttribute("dbOk",true);}catch(Exception e){m.addAttribute("dbOk",false);}return "index";}
 @GetMapping("/beneficiarios") public String beneficiarios(Model m){m.addAttribute("itens",factory.beneficiarios().listar());m.addAttribute("regra",regra.descricao());return "beneficiarios";}
 @GetMapping("/beneficiarios/novo") public String novoBeneficiario(Model m){m.addAttribute("item",new java.util.HashMap<>());return "beneficiario-form";}
 @GetMapping("/beneficiarios/editar/{id}") public String editarBeneficiario(@PathVariable int id,Model m){m.addAttribute("item",factory.beneficiarios().buscar(id));return "beneficiario-form";}
 @PostMapping("/beneficiarios/salvar") public String salvarBeneficiario(@RequestParam(required=false) Integer id,@RequestParam String nome,@RequestParam String cpf,@RequestParam(required=false) String telefone,@RequestParam(required=false) String endereco,@RequestParam(defaultValue="0") BigDecimal renda,@RequestParam(defaultValue="ATIVO") String status,RedirectAttributes ra){try{factory.beneficiarios().salvar(id,nome,cpf,telefone,endereco,renda,status);ra.addFlashAttribute("sucesso","Beneficiário salvo com sucesso.");}catch(Exception e){ra.addFlashAttribute("erro","Não foi possível salvar. Confira se o CPF já está cadastrado.");}return "redirect:/beneficiarios";}
 @PostMapping("/beneficiarios/excluir/{id}") public String excluirBeneficiario(@PathVariable int id,RedirectAttributes ra){try{factory.beneficiarios().excluir(id);ra.addFlashAttribute("sucesso","Beneficiário excluído.");}catch(Exception e){ra.addFlashAttribute("erro","Não foi possível excluir: pode existir retirada vinculada.");}return "redirect:/beneficiarios";}
 @GetMapping("/botijoes") public String botijoes(Model m){m.addAttribute("itens",factory.botijoes().listar());return "botijoes";}
 @GetMapping("/botijoes/novo") public String novoBotijao(Model m){m.addAttribute("item",new java.util.HashMap<>());return "botijao-form";}
 @GetMapping("/botijoes/editar/{id}") public String editarBotijao(@PathVariable int id,Model m){m.addAttribute("item",factory.botijoes().buscar(id));return "botijao-form";}
 @PostMapping("/botijoes/salvar") public String salvarBotijao(@RequestParam(required=false) Integer id,@RequestParam String codigo,@RequestParam String tipo,@RequestParam(defaultValue="0") BigDecimal valor,@RequestParam(defaultValue="0") int estoque,@RequestParam(defaultValue="DISPONIVEL") String status,RedirectAttributes ra){try{factory.botijoes().salvar(id,codigo,tipo,valor,estoque,status);ra.addFlashAttribute("sucesso","Botijão salvo com sucesso.");}catch(Exception e){ra.addFlashAttribute("erro","Não foi possível salvar. Confira se o código já existe.");}return "redirect:/botijoes";}
 @PostMapping("/botijoes/excluir/{id}") public String excluirBotijao(@PathVariable int id,RedirectAttributes ra){try{factory.botijoes().excluir(id);ra.addFlashAttribute("sucesso","Botijão excluído.");}catch(Exception e){ra.addFlashAttribute("erro","Não foi possível excluir: pode existir retirada vinculada.");}return "redirect:/botijoes";}
 @GetMapping("/retiradas") public String retiradas(Model m){m.addAttribute("itens",factory.retiradas().listar());m.addAttribute("beneficiarios",factory.beneficiarios().listar());m.addAttribute("botijoes",factory.botijoes().listar());return "retiradas";}
 @PostMapping("/retiradas/salvar") public String salvarRetirada(@RequestParam int beneficiario,@RequestParam int botijao,@RequestParam String data,@RequestParam int quantidade,RedirectAttributes ra){try{factory.retiradas().salvar(beneficiario,botijao,Date.valueOf(data),quantidade);ra.addFlashAttribute("sucesso","Retirada registrada.");}catch(Exception e){ra.addFlashAttribute("erro","Não foi possível registrar a retirada. Verifique os dados e o estoque.");}return "redirect:/retiradas";}
 @PostMapping("/retiradas/excluir/{id}") public String excluirRetirada(@PathVariable int id,RedirectAttributes ra){try{factory.retiradas().excluir(id);ra.addFlashAttribute("sucesso","Retirada excluída.");}catch(Exception e){ra.addFlashAttribute("erro","Não foi possível excluir a retirada.");}return "redirect:/retiradas";}
}
