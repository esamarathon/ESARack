package com.esamarathon.ESARack.hardware.test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Reader;
import java.net.Socket;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import com.esamarathon.ESARack.hardware.IN1606;
import com.esamarathon.ESARack.hardware.enums.AutoImageType;

public class TestIN1606<T> {
	
	private static final String linesep = System.getProperty("line.separator");
	
	@Test public void TestGetInput() throws IOException, InterruptedException {
		byte[] expectedDataSent = ("!"+linesep).getBytes();
		byte[] response = ("1"+linesep).getBytes();
		String expectedResult = "1";
		testCaseForGet((IN1606 in1606) -> in1606.getInput(), expectedDataSent, response, expectedResult);
	}

	@Test public void TestSetInput() throws IOException, InterruptedException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		String input = String.valueOf(1+Math.round(Math.random() * 5));
		IN1606 in1606 = getMockedIN1606(out, ("IN"+input+" All\r\n").getBytes());
		in1606.setInput(input);
		
		Assert.assertArrayEquals((input+"!" + linesep).getBytes(), out.toByteArray()); 
	}
	
	@Test public void TestGetWidth() throws IOException, InterruptedException {
		byte[] expectedDataSent = (IN1606.ESC + "HSIZ\r" + linesep).getBytes();
		byte[] response = ("50\r\n").getBytes();
		Integer expectedResult = 50;
		testCaseForGet((IN1606 in1606) -> in1606.getWidth(), expectedDataSent, response, expectedResult);
	}
	
	@Test public void TestSetWidth() throws IOException, InterruptedException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int input = 345;
		IN1606 in1606 = getMockedIN1606(out, ("HSIZ"+input+linesep).getBytes());
		in1606.setWidth(input);
		String expected = IN1606.ESC + String.valueOf(input) + "HSIZ\r"+linesep;
		String actual = out.toString();
		Assert.assertEquals("Should be equal.", expected, actual); 
	}
	
	@Test public void TestGetHeight() throws IOException, InterruptedException {
		byte[] expectedDataSent = (IN1606.ESC + "VSIZ\r"+linesep).getBytes();
		byte[] response = ("75"+linesep).getBytes();
		Integer expectedResult = 75;
		testCaseForGet((IN1606 in1606) -> in1606.getHeight(), expectedDataSent, response, expectedResult);
	}
	
	@Test public void TestSetHeight() throws IOException, InterruptedException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int input = 4096;
		IN1606 in1606 = getMockedIN1606(out, ("VSIZ"+input+"\r\n").getBytes());
		in1606.setHeight(input);
		String expected = IN1606.ESC + String.valueOf(input) + "VSIZ\r"+linesep;
		String actual = out.toString();
		Assert.assertEquals("Should be equal.", expected, actual); 
	}
	
	@Test public void TestExecuteAutoImage_Fill() throws IOException, InterruptedException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		IN1606 in1606 = getMockedIN1606(out, ("Img1\r\n").getBytes());
		in1606.executeAutoMode(AutoImageType.Fill);
		String expected = "1*A"+linesep;
		String actual = out.toString();
		Assert.assertEquals("Should be equal.", expected, actual); 
	}
	
	@Test public void TestExecuteAutoImage_Follow() throws IOException, InterruptedException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		IN1606 in1606 = getMockedIN1606(out, ("Img2\r\n").getBytes());
		in1606.executeAutoMode(AutoImageType.Follow);
		String expected = "2*A"+linesep;
		String actual = out.toString();
		Assert.assertEquals("Should be equal.", expected, actual); 
	}
	
	@Test public void TestExecuteAutoImage_Null() throws IOException, InterruptedException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		IN1606 in1606 = getMockedIN1606(out, ("Img1\r\n").getBytes());
		try {
			in1606.executeAutoMode(null);
			Assert.fail("Should throw illegal argument exception.");
		} catch (IllegalArgumentException e) {
			
		}
	}
	
	private <T> void testCaseForGet(TestFunction<T> f,  byte[] expectedInput, byte[] outResponse, T expected) throws IOException, InterruptedException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		IN1606 in1606 = getMockedIN1606(out, outResponse);
		T actual = f.apply(in1606);
		Assert.assertEquals(expected, actual);
		Assert.assertArrayEquals(expectedInput, out.toByteArray()); 
	}
	
	private interface TestFunction<T> {
		T apply(IN1606 in1606) throws IOException, InterruptedException;
	}
	
	private IN1606 getMockedIN1606(final ByteArrayOutputStream out, byte[] answer) throws IOException {
		Socket socket = Mockito.mock(Socket.class);
		byte[] buf = answer;
		final ByteArrayInputStream in = new ByteArrayInputStream(buf);
		Mockito.when(socket.getOutputStream()).thenReturn(out);
		Mockito.when(socket.getInputStream()).thenReturn(in);
		
		IN1606 in1606 = new IN1606("192.168.254.254", 23) {
			
			protected void readCopyrightMessage(Reader in) throws IOException, InterruptedException {
				//Do nothing since we will not send any copyright message here.
			}
			
			protected Socket createSocket() throws IOException {
				return socket;
			}
			
		};
		return in1606;
	}
}
