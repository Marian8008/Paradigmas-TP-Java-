package tpparadigmas;

public enum TipoAtraccion {
	Aventura,
	Paisaje,
	Degustacion;
	
	public static TipoAtraccion asignarTipo(String nombreTipoAtraccion) throws Exception
	{
		TipoAtraccion tipo;
		switch(nombreTipoAtraccion)				//Asignacion de tipoAtraccion
		{
		case "Paisaje":
			tipo = TipoAtraccion.Paisaje;
			break;
		case "Degustacion":
			tipo = TipoAtraccion.Degustacion;
			break;
		case "Aventura":
			tipo = TipoAtraccion.Aventura;
			break;
		default:
			throw new Exception(nombreTipoAtraccion+ "no es un tipo de atraccion valido");
		}
		
		return tipo;
	}
}
