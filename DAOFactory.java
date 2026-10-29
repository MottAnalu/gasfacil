package br.com.gasfacil.pattern;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import br.com.gasfacil.dao.*;
@Component
public class DAOFactory {
 private final JdbcTemplate jdbc;
 public DAOFactory(JdbcTemplate jdbc){ this.jdbc=jdbc; }
 public BeneficiarioDAO beneficiarios(){ return new BeneficiarioDAO(jdbc); }
 public BotijaoDAO botijoes(){ return new BotijaoDAO(jdbc); }
 public RetiradaDAO retiradas(){ return new RetiradaDAO(jdbc); }
}
