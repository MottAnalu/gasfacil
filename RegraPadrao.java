package br.com.gasfacil.pattern.strategy;
import java.math.BigDecimal;
import org.springframework.stereotype.Component;
@Component
public class RegraPadrao implements RegraBeneficio {
 public boolean elegivel(BigDecimal renda){ return renda != null && renda.compareTo(new BigDecimal("2500.00")) <= 0; }
 public String descricao(){ return "Renda familiar de até R$ 2.500,00"; }
}
