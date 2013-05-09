package at.ac.tuwien.infosys.www.pixy.conversion.nodes;

import java.util.Collections;
import java.util.List;

import at.ac.tuwien.infosys.www.phpparser.ParseNode;
import at.ac.tuwien.infosys.www.pixy.conversion.TacActualParam;
import at.ac.tuwien.infosys.www.pixy.conversion.TacFunction;
import at.ac.tuwien.infosys.www.pixy.conversion.TacPlace;
import at.ac.tuwien.infosys.www.pixy.conversion.Variable;

// *********************************************************************************
// CfgNodeCallPrep *****************************************************************
// *********************************************************************************

// almost identical to CfgNodeCall
// doesn't do 'function.addCall(this)' in the constructor and in
// setFunction; and has cbrPairs
/**
 * @author Nenad Jovanovic <enji@seclab.tuwien.ac.at>
 */
public class CfgNodeCallPrep
    extends CfgNode {

// CONSTRUCTORS ********************************************************************

    public CfgNodeCallPrep(ParseNode node) {
        super(node);
    }

// GET *****************************************************************************

    public TacFunction getCallee() {
        return this.getCallNode().getCallee();
    }

    public TacFunction getCaller() {
        return this.getCallNode().getEnclosingFunction();
    }

    public TacPlace getFunctionNamePlace() {
        return this.getCallNode().getFunctionNamePlace();
    }

    public List<TacActualParam> getParamList() {
        return this.getCallNode().getParamList();
    }

    public CfgNodeCallRet getCallRetNode() {
        return (CfgNodeCallRet) this.getSuccessor(0).getSuccessor(0);
    }

    public CfgNodeCall getCallNode() {
        return (CfgNodeCall) this.getSuccessor(0);
    }

    public List<Variable> getVariables() {
        return Collections.emptyList();
    }

    // returns a list consisting of two-element-lists consisting of
    // (actual cbr-param, formal cbr-param) (Variable objects)
    public List getCbrParams() {
        return this.getCallNode().getCbrParams();
    }

// SET *****************************************************************************

    public void replaceVariable(int index, Variable replacement) {
        // do nothing
    }
}