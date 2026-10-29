package br.com.gasfacil.dao;
import java.util.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
@Repository
public class BeneficiarioDAO {
 private final JdbcTemplate jdbc;
 public BeneficiarioDAO(JdbcTemplate jdbc){this.jdbc=jdbc;}
 private final RowMapper<Map<String,Object>> mapper=(r,n)->{Map<String,Object> m=new LinkedHashMap<>(); m.put("id",r.getInt("id_beneficiario"));m.put("nome",r.getString("nome"));m.put("cpf",r.getString("cpf"));m.put("telefone",r.getString("telefone"));m.put("endereco",r.getString("endereco"));m.put("renda",r.getBigDecimal("renda_familiar"));m.put("status",r.getString("status"));return m;};
 public List<Map<String,Object>> listar(){return jdbc.query("SELECT * FROM beneficiario ORDER BY nome",mapper);}
 public void salvar(Integer id,String nome,String cpf,String telefone,String endereco,java.math.BigDecimal renda,String status){
  if(id==null) jdbc.update("INSERT INTO beneficiario(nome,cpf,telefone,endereco,renda_familiar,status) VALUES(?,?,?,?,?,?)",nome,cpf,telefone,endereco,renda,status);
  else jdbc.update("UPDATE beneficiario SET nome=?,cpf=?,telefone=?,endereco=?,renda_familiar=?,status=? WHERE id_beneficiario=?",nome,cpf,telefone,endereco,renda,status,id);
 }
 public Map<String,Object> buscar(int id){return jdbc.queryForObject("SELECT * FROM beneficiario WHERE id_beneficiario=?",mapper,id);}
 public void excluir(int id){jdbc.update("DELETE FROM beneficiario WHERE id_beneficiario=?",id);}
}
