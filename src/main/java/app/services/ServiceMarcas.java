package app.services;

import java.io.IOException;
import java.util.List;

import app.entity.Marcas;
import app.fibeInterfaces.InterfaceMarcas;
import retrofit2.Call;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;

/**
 * Classe que representa ...
 * 
 * @author Rodrigo Gregorio Neri
 * @version 1.0
 * @since 2017-07-20
 *
 */

public class ServiceMarcas {

	Retrofit retrofit = new Retrofit.Builder().baseUrl(InterfaceMarcas.API_URL)
			.addConverterFactory(GsonConverterFactory.create()).build();

	public List<Marcas> getListMarcas() throws IOException{
		InterfaceMarcas marcasinterface = retrofit.create(InterfaceMarcas.class);
		Call<List<Marcas>> call = marcasinterface.marcas();
		List<Marcas> marcas = call.execute().body();

		return marcas;
	}

}
