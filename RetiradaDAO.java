package br.com.gasfacil.dao;
import java.util.*;
import org.springframework.jdbc.core.*;
public class RetiradaDAO {
 private final JdbcTemplate jdbc; public RetiradaDAO(JdbcTemplate jdbc){this.jdbc=jdbc;}
 private final RowMapper<Map<String,Object>> mapper=(r,n)->{Map<String,Object> m=new LinkedHashMap<>();m.put("id",r.getInt("id_retirada"));m.put("beneficiario",r.getString("beneficiario"));m.put("botijao",r.getString("botijao"));m.put("data",r.getDate("data_retirada").toLocalDate());m.put("quantidade",r.getInt("quantidade"));m.put("status",r.getString("status"));return m;};
 public List<Map<String,Object>> listar(){return jdbc.query("SELECT r.id_retirada,b.nome beneficiario,CONCAT(g.codigo,' - ',g.tipo) botijao,r.data_retirada,r.quantidade,r.status FROM retirada r JOIN beneficiario b ON b.id_beneficiario=r.id_beneficiario JOIN botijao g ON g.id_botijao=r.id_botijao ORDER BY r.data_retirada DESC",mapper);}
 public void salvar(int beneficiario,int botijao,java.sql.Date data,int quantidade){jdbc.update("INSERT INTO retirada(id_beneficiario,id_botijao,data_retirada,quantidade,status) VALUES(?,?,?,?, 'CONCLUIDA')",beneficiario,botijao,data,quantidade);jdbc.update("UPDATE botijao SET estoque=GREATEST(estoque-?,0) WHERE id_botijao=?",quantidade,botijao);}
 public void excluir(int id){jdbc.update("DELETE FROM retirada WHERE id_retirada=?",id);}
}
