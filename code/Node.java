/**
 * @author Zhiqiang Ren 
 * date: Feb. 4th. 2012/Users/jungwoonshin/Dropbox/cs440_p1_handgestures/P2/src/aipackage/Connection.java
/Users/jungwoonshin/Dropbox/cs440_p1_handgestures/P2/src/aipackage/DataProcessor.java
/Users/jungwoonshin/Dropbox/cs440_p1_handgestures/P2/src/aipackage/NeuralNet.java
/Users/jungwoonshin/Dropbox/cs440_p1_handgestures/P2/src/aipackage/NeuralNetLearner.java
/Users/jungwoonshin/Dropbox/cs440_p1_handgestures/P2/src/aipackage/Node.java
 * 
 */

package p2;

import java.util.ArrayList;
import java.util.List;

public class Node {
    public Node(int layer, int pos, boolean isThreshold)
    {
        m_input = 0;
        m_output = 0;
        m_input_conn = new ArrayList<Connection>();
        m_output_conn = new ArrayList<Connection>();
        
        m_beta = 0;
        
        m_pos = pos;
        m_layer = layer;
    }
    
    public void addInputConnection(Connection con) {
        m_input_conn.add(con);
    }
    
    public void addOutputConnection(Connection con) {
        m_output_conn.add(con);
    }
    
    public void setOutput(double output) {
        m_output = output;
    }
    
    public double getOutput() {
        return m_output;
    }
    
    
    public double f(double sigma)
    {
        return 1 / (1 + Math.exp(-1 * sigma));        
    }
        
    public double getBeta() {
        return m_beta;
    }
    
    public Connection getOutputConnection(int j) {
        return m_output_conn.get(j);
    }
    
    public List<Connection> getInputConnectionList() {
        return m_input_conn;        
    }
    
    public List<Connection> getOutputConnectionList() {
    	return m_output_conn; 
    }
    
    public int getPos() {
        return m_pos;
    }
    
    public int getLayer() {
        return m_layer;
    }
    
    public void calcOutput() {
        m_input = 0;
        m_output = 0;

        for (Connection con: m_input_conn) {
            m_input += (con.getWeight() * con.getFromNode().getOutput());
        }
        m_output = f(m_input);
    }
    
    // Computes error for output layer
    public void calcBetaOutput(double target, double rate) {
    	m_beta = target - m_output; 
    	
    	// Update deltaw for each input connection
    	for (Connection c: m_input_conn) {
    		c.calcDeltaw(rate);
    		c.updateWeight();
    	}
    }
    
    // Computes error for hidden layers
    public void calcBetaHidden(double rate) {
    	
    	double _m_beta = 0; 
    	
    	for (Connection c: m_output_conn) {
    		double weight = c.getWeight();
    		double dstNodeOutput = c.getToNode().getOutput();
    		double dstNodeBeta = c.getToNode().getBeta();
    		_m_beta += weight * dstNodeOutput * (1 - dstNodeOutput) * dstNodeBeta;
//    		System.out.println("+");
    	}
    	
    	m_beta = _m_beta;
    	
    	// Update deltaw for each input connection 
    	for (Connection c: m_input_conn) {
    		c.calcDeltaw(rate);
    		c.updateWeight();
    	}
    	
    	/*
    	Connection c = m_output_conn.get(m_output_conn.size()-1);
    	double weight = c.getWeight();
		double dstNodeOutput = c.getToNode().getOutput();
		double dstNodeBeta = c.getToNode().getBeta();
		_m_beta += weight * dstNodeOutput * (1 - dstNodeOutput) * dstNodeBeta;
		m_beta = _m_beta;
//    	Connection c = m_output_conn.get(m_output_conn.size());
		c.calcDeltaw(rate);
		c.updateWeight();
		*/
    	
    }
        
    private List<Connection> m_input_conn;
    private List<Connection> m_output_conn;
    
    private double m_input;
    private double m_output;
    
    private double m_beta;
    
    private int m_pos;  // starting from 0
    private int m_layer;  // starting form 0
}


