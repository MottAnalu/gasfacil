package br.com.gasfacil.dao;
import java.util.*;
import org.springframework.jdbc.core.*;
import org.springframework.stereotype.Repository;
public class BotijaoDAO {
 private final JdbcTemplate jdbc; public BotijaoDAO(JdbcTemplate jdbc){this.jdbc=jdbc;}
 private final RowMapper<Map<String,Object>> mapper=(r,n)->{Map<String,Object> m=new LinkedHashMap<>();m.put("id",r.getInt("id_botijao"));m.put("codigo",r.getString("codigo"));m.put("tipo",r.getString("tipo"));m.put("valor",r.getBigDecimal("valor"));m.put("estoque",r.getInt("estoque"));m.put("status",r.getString("status"));return m;};
 public List<Map<String,Object>> listar(){return jdbc.query("SELECT * FROM botijao ORDER BY codigo",mapper);}
 public Map<String,Object> buscar(int id){return jdbc.queryForObject("SELECT * FROM botijao WHERE id_botijao=?",mapper,id);}
 public void salvar(Integer id,String codigo,String tipo,java.math.BigDecimal valor,int estoque,String status){if(id==null)jdbc.update("INSERT INTO botijao(codigo,tipo,valor,estoque,status) VALUES(?,?,?,?,?)",codigo,tipo,valor,estoque,status);else jdbc.update("UPDATE botijao SET codigo=?,tipo=?,valor=?,estoque=?,status=? WHERE id_botijao=?",codigo,tipo,valor,estoque,status,id);}
 public void excluir(int id){jdbc.update("DELETE FROM botijao WHERE id_botijao=?",id);}
}
