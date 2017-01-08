package com.esamarathon.ESARack.hardware.test;

import org.junit.*;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

import com.esamarathon.ESARack.hardware.VP50;
import com.esamarathon.ESARack.hardware.enums.VP50Inputs;

import jssc.SerialPort;

public class TestVP50 {

	@Mock SerialPort port;
	VP50 vp50;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		vp50 = new VP50(port);
	}
	
	@Test public void TestTurnOn() throws Exception {
		ArgumentCaptor<byte[]> args = ArgumentCaptor.forClass(byte[].class);	
		vp50.turnOn();
		verify(port).writeBytes(args.capture());
		Assert.assertArrayEquals("Byte sequence for turning on the VP50", 
				new byte[]{0x02, 0x33, 0x30, 0x30, 0x35, 0x41, 0x31, 0x00, 0x31, 0x00, 0x03}, 
				args.getValue());
	}
	
	@Test public void TestTurnOff() throws Exception {
		ArgumentCaptor<byte[]> args = ArgumentCaptor.forClass(byte[].class);	
		vp50.turnOff();
		verify(port).writeBytes(args.capture());
		Assert.assertArrayEquals("Byte sequence for turning off the VP50", 
				new byte[]{0x02, 0x33, 0x30, 0x30, 0x35, 0x41, 0x31, 0x00, 0x30, 0x00, 0x03}, 
				args.getValue());
	}
	
	@Test public void TestSetInputHDMI1() throws Exception {
		ArgumentCaptor<byte[]> args = ArgumentCaptor.forClass(byte[].class);	
		vp50.setInput(VP50Inputs.HDMI1);;
		verify(port).writeBytes(args.capture());
		Assert.assertArrayEquals("Byte sequence for switching input to HDMI1", 
				new byte[]{0x02, 0x33, 0x30, 0x30, 0x35, 0x41, 0x43, 0x00, 0x38, 0x00, 0x03}, 
				args.getValue());
	}
	
	@Test public void TestSetInputSDI2() throws Exception {
		ArgumentCaptor<byte[]> args = ArgumentCaptor.forClass(byte[].class);	
		vp50.setInput(VP50Inputs.SDI2);;
		verify(port).writeBytes(args.capture());
		Assert.assertArrayEquals("Byte sequence for switching input to SDI2", 
				new byte[]{0x02, 0x33, 0x30, 0x30, 0x36, 0x41, 0x43, 0x00, 0x31, 0x34, 0x00, 0x03}, 
				args.getValue());
	}
}
