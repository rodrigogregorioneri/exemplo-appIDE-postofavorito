package app.business;

import java.util.List;

import org.springframework.stereotype.Service;

import app.entity.Marcas;
import app.services.ServiceFipe;
import retrofit2.Call;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Classe que representa ...
 * 
 * @author Rodrigo Gregorio Neri
 * @version 1.0
 * @since 2017-07-31
 *
 */

@Service("MarcasBusiness")
public class MarcasBusiness {
  
	public List<Marcas> listarTodasMarcas( ) {
		Retrofit retrofit = new Retrofit.Builder().baseUrl(ServiceFipe.API_URL)
				.addConverterFactory(GsonConverterFactory.create()).build();
		List<Marcas> marcas = null;
	  ServiceFipe mc = retrofit.create(ServiceFipe.class);
		
		Call<List<Marcas>> call = mc.listatodasmarcas();
		Call<List<Marcas>> call2 = call.clone();
		Response<List<Marcas>> response = null;
		try {
			response = call2.execute();
			marcas = response.body();
			if (marcas != null) {
					return marcas;
			}
			return marcas;
		} catch (Exception e) {
			  e.printStackTrace();
		}
		return marcas;
	}


}
