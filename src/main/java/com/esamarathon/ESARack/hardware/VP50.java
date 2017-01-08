package com.esamarathon.ESARack.hardware;

import java.nio.ByteBuffer;
import java.util.logging.Logger;

import com.esamarathon.ESARack.hardware.enums.VP50Inputs;

import jssc.*;

/**
 * VP50 är den som ska ändras på minst
 * Byta inputs [Must]
 * Recall presets [Nice to have](edited)
 * Det är den enda som VP50 ska behöva göra
 * 
 * @author zephyyrr
 *
 */
public class VP50 implements PowerSwitch, PresetSwitch, InputSwitch {
	
	private Logger logger;
	private SerialPort port;
	
	protected static final byte BEGINCOMMS = 0x02;
	protected static final byte ENDCOMMS = 0x03;
	protected static final short COMMAND = 0x3330;
	protected static final byte VALUEWRAPPER = 0x00;
	
	public VP50(SerialPort port) {
		logger = Logger.getLogger("VP50");
		this.port = port;
	}
	
	/* (non-Javadoc)
	 * @see com.esamarathon.ESARack.hardware.Power#turnOn()
	 */
	public void turnOn() throws Exception {
		logger.info("Turning on.");
		port.writeBytes(createCommand((short) 0x4131, (byte) 0x31));
		byte[] answer = port.readBytes();
		//Check answer to make sure things went well.
	}
	
	/* (non-Javadoc)
	 * @see com.esamarathon.ESARack.hardware.Power#turnOff()
	 */
	public void turnOff() throws Exception {
		logger.info("Turning off.");
		port.writeBytes(createCommand((short) 0x4131, (byte) 0x30));
		byte[] answer = port.readBytes();
		//Check answer to make sure things went well.
	}
	
	/* (non-Javadoc)
	 * @see com.esamarathon.ESARack.hardware.InputSwitch#getInput()
	 */
	@Override
	public String getInput() {
		return VP50Inputs.HDMI1.toString();
		
	}
	
	public void setInput(VP50Inputs input) throws Exception {
		logger.info("Setting input to "+ input.toString() + ".");
		port.writeBytes(createCommand((short) 0x4143, input.GetControlValue()));
	}
	
	/* (non-Javadoc)
	 * @see com.esamarathon.ESARack.hardware.InputSwitch#setInput(java.lang.String)
	 */
	@Override
	public void setInput(String input) throws Exception {
		setInput(VP50Inputs.valueOf(input));
	}
	
	/* (non-Javadoc)
	 * @see com.esamarathon.ESARack.hardware.Preset#setPreset(int)
	 */
	@Override
	public void setPreset(int preset) {
		logger.info("Switching to preset " + preset + ".");
	}
	
	/* (non-Javadoc)
	 * @see com.esamarathon.ESARack.hardware.Preset#getPreset()
	 */
	@Override
	public int getPreset() {
		int preset = 1;
		logger.info("Getting current preset " + preset + ".");
		return preset;
	}
	
	protected byte[] createCommand(short command, byte[] value) {
		byte length = (byte) (4+ value.length);
		ByteBuffer buf = ByteBuffer.allocate(length + 4);
		fillCommandHeader(buf, command, length);
		buf.put(value);
		fillCommandTrailer(buf);
		return buf.array();
	}
	
	protected byte[] createCommand(short command, byte value) {
		ByteBuffer buf = ByteBuffer.allocate(11);
		fillCommandHeader(buf, command, (byte)5);
		buf.put(value);
		fillCommandTrailer(buf);
		return buf.array();
	}
	
	protected byte[] createCommand(short command, short value) {
		if (value < 0x100) return createCommand(command, (byte) value);
		
		ByteBuffer buf = ByteBuffer.allocate(12);
		fillCommandHeader(buf, command, (byte)(6));
		buf.putShort(value);
		fillCommandTrailer(buf);
		return buf.array();
	}
	
	private void fillCommandHeader(ByteBuffer buf, short command, byte length) {
		buf.put(BEGINCOMMS);
		buf.putShort(COMMAND);
		buf.putShort(ASCIIEncode(length));
		buf.putShort(command);
		buf.put(VALUEWRAPPER);
	}
	
	private void fillCommandTrailer(ByteBuffer buf) {
		buf.put(VALUEWRAPPER);
		//Insert checksum calculation result here.
		//SInce it is optional I have decided to skip it for now.
		buf.put(ENDCOMMS);
	}

	private short ASCIIEncode(byte length) {
		return (short) (0x30 + (length % 10) + 0x3000 + (length/10 %10)*10);
	}
}
