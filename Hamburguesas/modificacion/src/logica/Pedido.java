package logica;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Random;

import logica.Producto;

public class Pedido {
	
	//ATRIBUTOS
	private int numero_pedidos;
	private int idPedido;
	private String nombreCliente;
	private String direccionCliente;
	private ArrayList<Producto> lista_productos;
	
//CONSTRUCTORES
	public Pedido(String nombreCliente, String direccionCliente,ArrayList<Producto> lista_productos ) 
	{
		super();
		this.numero_pedidos = 1;
		Random rd = new Random();
		this.idPedido = rd.nextInt(10001);
		this.nombreCliente = nombreCliente;
		this.direccionCliente = direccionCliente;
		this.lista_productos= lista_productos;
	}
	
//getters
	

	public int getIdPedido() {
		return idPedido;
	}
	public String getNombreCliente() {
		return nombreCliente;
	}
	public String getDireccionCliente() {
		return direccionCliente;
	}
	
	public void agregarProducto(Producto nuevoItem) {
		lista_productos.add(nuevoItem);
	}
	public int getPrecioNetoPedido() {
		int precioNeto=0;
		for (int i = 0; i<lista_productos.size(); i++) {
			precioNeto= precioNeto + lista_productos.get(i).getPrecio();
		}
		return precioNeto;
	}
	public double getPrecioIvaPedido() {
		int precioNeto= getPrecioNetoPedido();
		return (precioNeto*0.19);	
	}
	
	public double getPrecioTotalPedido() {
		return (getPrecioNetoPedido()+getPrecioIvaPedido());
		
	}
	public ArrayList<String> generarTextoFactura() {
		ArrayList<String> txtFacturaPedido= new ArrayList<String>();
		for (int i = 0; i<lista_productos.size(); i++) {
			txtFacturaPedido.add(lista_productos.get(i).generarTextoFactura());
		}
		return txtFacturaPedido;
		
	}
	public int getTotalCalorias() {
		int calorias=0;
		for (int i = 0; i<lista_productos.size(); i++) {
			calorias= calorias + lista_productos.get(i).getCalorias();
		}
		return calorias;
	}
	public void guardarFactura() throws FileNotFoundException, UnsupportedEncodingException {
		ArrayList<String> textoFactura= generarTextoFactura();
		PrintWriter writer = new PrintWriter("./data/" + idPedido, "UTF-8");
		writer.println("ID del pedido; " + this.idPedido);
		writer.println("Nombre del cliente; " + this.nombreCliente);
		writer.println("Direcci??n del cliente; " + this.direccionCliente);
		
		for (int i = 0; i<textoFactura.size(); i++) {
			writer.println(textoFactura.get(i));
		}
		writer.println("Total Calor??as; " + getTotalCalorias());
		writer.println("Precio Neto; " + getPrecioNetoPedido());
		writer.println("Precio Iva; " + getPrecioIvaPedido());
		writer.println("Total a pagar; " + getPrecioTotalPedido());
        writer.close();
	}
	


}
