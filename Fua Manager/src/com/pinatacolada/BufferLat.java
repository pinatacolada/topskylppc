package com.pinatacolada;

/**
 * @author rtpso
 * 
 * @BufferU High level buffer (nautical miles, decimal value, range 0.0-999.0)
 * @BufferLI Low level buffer for IFR flights (nautical miles, decimal value, range 0.0-999.0)
 * @BufferLV Low level buffer for VFR flights (nautical miles, decimal value, range 0.0-999.0)
 */
public class BufferLat {
	public BufferLat(String high, String lowI, String lowV) {
		bufferU = high;
		bufferLI = lowI;
		bufferLV = lowV;		
	}
	private String bufferU;
	private String bufferLI;
	private String bufferLV;
}
