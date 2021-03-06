package app.business;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.*;
import app.dao.*;
import app.entity.*;

import org.springframework.data.domain.PageImpl;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Classe que representa a camada de negócios de CarroBusiness
 * 
 * @generated
 **/
@Service("CarroBusiness")
public class CarroBusiness {

  private static final Logger log = LoggerFactory.getLogger(CarroBusiness.class);


  /**
   * Instância da classe CarroDAO que faz o acesso ao banco de dados
   * 
   * @generated
   */
  @Autowired
  @Qualifier("CarroDAO")
  protected CarroDAO repository;
  
   @Autowired
   @Qualifier("UserDAO")
   protected UserDAO userDAO;

  // CRUD

  /**
   * Serviço exposto para novo registro de acordo com a entidade fornecida
   * 
   * @generated
   */
  public Carro post(final Carro entity, HttpServletRequest req) throws Exception {
    // begin-user-code  
    // end-user-code  
     String username = (String)req.getSession().getAttribute("username");
 
     
     User user = userDAO.userByLogin(username);
     
     entity.setUser(user);
     		
    
     Carro result = repository.save(entity);
    // begin-user-code
    // end-user-code
    return result;
  }

  /**
   * Serviço exposto para salvar alterações de acordo com a entidade fornecida
   * 
   * @generated
   */
  public Carro put(final Carro entity) throws Exception {
    // begin-user-code  
    // end-user-code
    Carro result = null;
    result = repository.saveAndFlush(entity);
    // begin-user-code
    // end-user-code
    return result;
  }

  /**
   * Serviço exposto para remover a entidade de acordo com o id fornecido
   * 
   * @generated
   */
  public void delete(java.lang.String id) throws Exception {
    // begin-user-code  
    // end-user-code
    try {
     Carro entity = this.get(id);
     this.repository.delete(entity);
    }catch(Exception e) {
        throw new Exception("Por favor, delete primeiro os abastecimentos deste carro.");
    } 
    // begin-user-code  
    // end-user-code        
  }
  
  /**
   * Serviço exposto para recuperar a entidade de acordo com o id fornecido
   * 
   * @generated
   */
  public Carro get(java.lang.String id) throws Exception {
    // begin-user-code  
    // end-user-code
    Carro result = repository.findOne(id);
    // begin-user-code
    // end-user-code
    return result;
  }

  // CRUD
  
  /**
   * Lista com paginação de acordo com a NamedQuery
   * 
   * @generated
   */
  public Page<Carro> list(Pageable pageable){
    // begin-user-code
    // end-user-code
    Page<Carro> result = repository.list(pageable);
    // begin-user-code
    // end-user-code
    return result;
  }
  
  /**
   * @generated modifiable
   * OneToMany Relation
   */  
  public Page<Abastecimento> findAbastecimento(java.lang.String id, Pageable pageable) {
    // begin-user-code
    // end-user-code  
    Page<Abastecimento> result = repository.findAbastecimento(id, pageable);
    // begin-user-code  
    // end-user-code        
    return result;    
  }
  
  public Page<CarroVO> findConsumoMedio(java.lang.String id, Pageable pageable){
      // begin-user-code
      // end-user-code 
      List<Abastecimento> abastecimentosDeUmCarro;
      List<CarroVO> listaMediaConsumo = new ArrayList<CarroVO>();
      Carro carro = new Carro();
      
      carro = repository.findOne(id);
     
     CarroVO carroVO;
     
     Double acm = 0.0;
     Double mediaConsumo = 0.0;
     
     Page<Abastecimento> result = repository.findAbastecimento(id, pageable);
     
     abastecimentosDeUmCarro = result.getContent();
     
     
     for(Abastecimento abastecimento: abastecimentosDeUmCarro){
          acm = acm + (abastecimento.getQuilometragemRodada()/abastecimento.getLitros());
      }
      
      if(abastecimentosDeUmCarro.size() == 0)
         mediaConsumo= 0.0;
      else
         mediaConsumo = acm/abastecimentosDeUmCarro.size(); 
         
       
      
       carroVO = new CarroVO(mediaConsumo, carro.getPlaca());
       listaMediaConsumo.add(carroVO);
       
      final Page<CarroVO> retorno = new PageImpl<>(listaMediaConsumo);
     
      return retorno;
  }
  
  public Page<Carro> findCarrosByUser(java.lang.String instanceId, Pageable pageable) {
      // begin-user-code
      // end-user-code  
      Page<Carro> result = repository.findCarrosByUser(instanceId, pageable);
      // begin-user-code  
      // end-user-code        
      return result;
  }
  
  
}