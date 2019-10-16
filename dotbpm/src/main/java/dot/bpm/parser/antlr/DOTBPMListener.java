// Generated from E:/Brennan/Projects/workhorse/dotbpm/src/main/java/dot/bpm/parser/antlr\DOTBPM.g4 by ANTLR 4.7.2
package dot.bpm.parser.antlr;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link DOTBPMParser}.
 */
public interface DOTBPMListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link DOTBPMParser#process}.
	 * @param ctx the parse tree
	 */
	void enterProcess(DOTBPMParser.ProcessContext ctx);
	/**
	 * Exit a parse tree produced by {@link DOTBPMParser#process}.
	 * @param ctx the parse tree
	 */
	void exitProcess(DOTBPMParser.ProcessContext ctx);
	/**
	 * Enter a parse tree produced by {@link DOTBPMParser#stmt_list}.
	 * @param ctx the parse tree
	 */
	void enterStmt_list(DOTBPMParser.Stmt_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link DOTBPMParser#stmt_list}.
	 * @param ctx the parse tree
	 */
	void exitStmt_list(DOTBPMParser.Stmt_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link DOTBPMParser#stmt}.
	 * @param ctx the parse tree
	 */
	void enterStmt(DOTBPMParser.StmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link DOTBPMParser#stmt}.
	 * @param ctx the parse tree
	 */
	void exitStmt(DOTBPMParser.StmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link DOTBPMParser#activity}.
	 * @param ctx the parse tree
	 */
	void enterActivity(DOTBPMParser.ActivityContext ctx);
	/**
	 * Exit a parse tree produced by {@link DOTBPMParser#activity}.
	 * @param ctx the parse tree
	 */
	void exitActivity(DOTBPMParser.ActivityContext ctx);
	/**
	 * Enter a parse tree produced by {@link DOTBPMParser#activity_type}.
	 * @param ctx the parse tree
	 */
	void enterActivity_type(DOTBPMParser.Activity_typeContext ctx);
	/**
	 * Exit a parse tree produced by {@link DOTBPMParser#activity_type}.
	 * @param ctx the parse tree
	 */
	void exitActivity_type(DOTBPMParser.Activity_typeContext ctx);
	/**
	 * Enter a parse tree produced by {@link DOTBPMParser#event}.
	 * @param ctx the parse tree
	 */
	void enterEvent(DOTBPMParser.EventContext ctx);
	/**
	 * Exit a parse tree produced by {@link DOTBPMParser#event}.
	 * @param ctx the parse tree
	 */
	void exitEvent(DOTBPMParser.EventContext ctx);
	/**
	 * Enter a parse tree produced by {@link DOTBPMParser#event_type}.
	 * @param ctx the parse tree
	 */
	void enterEvent_type(DOTBPMParser.Event_typeContext ctx);
	/**
	 * Exit a parse tree produced by {@link DOTBPMParser#event_type}.
	 * @param ctx the parse tree
	 */
	void exitEvent_type(DOTBPMParser.Event_typeContext ctx);
	/**
	 * Enter a parse tree produced by {@link DOTBPMParser#gateway}.
	 * @param ctx the parse tree
	 */
	void enterGateway(DOTBPMParser.GatewayContext ctx);
	/**
	 * Exit a parse tree produced by {@link DOTBPMParser#gateway}.
	 * @param ctx the parse tree
	 */
	void exitGateway(DOTBPMParser.GatewayContext ctx);
	/**
	 * Enter a parse tree produced by {@link DOTBPMParser#gateway_type}.
	 * @param ctx the parse tree
	 */
	void enterGateway_type(DOTBPMParser.Gateway_typeContext ctx);
	/**
	 * Exit a parse tree produced by {@link DOTBPMParser#gateway_type}.
	 * @param ctx the parse tree
	 */
	void exitGateway_type(DOTBPMParser.Gateway_typeContext ctx);
	/**
	 * Enter a parse tree produced by {@link DOTBPMParser#association}.
	 * @param ctx the parse tree
	 */
	void enterAssociation(DOTBPMParser.AssociationContext ctx);
	/**
	 * Exit a parse tree produced by {@link DOTBPMParser#association}.
	 * @param ctx the parse tree
	 */
	void exitAssociation(DOTBPMParser.AssociationContext ctx);
	/**
	 * Enter a parse tree produced by {@link DOTBPMParser#seq_flow}.
	 * @param ctx the parse tree
	 */
	void enterSeq_flow(DOTBPMParser.Seq_flowContext ctx);
	/**
	 * Exit a parse tree produced by {@link DOTBPMParser#seq_flow}.
	 * @param ctx the parse tree
	 */
	void exitSeq_flow(DOTBPMParser.Seq_flowContext ctx);
	/**
	 * Enter a parse tree produced by {@link DOTBPMParser#msg_flow}.
	 * @param ctx the parse tree
	 */
	void enterMsg_flow(DOTBPMParser.Msg_flowContext ctx);
	/**
	 * Exit a parse tree produced by {@link DOTBPMParser#msg_flow}.
	 * @param ctx the parse tree
	 */
	void exitMsg_flow(DOTBPMParser.Msg_flowContext ctx);
	/**
	 * Enter a parse tree produced by {@link DOTBPMParser#attr_list}.
	 * @param ctx the parse tree
	 */
	void enterAttr_list(DOTBPMParser.Attr_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link DOTBPMParser#attr_list}.
	 * @param ctx the parse tree
	 */
	void exitAttr_list(DOTBPMParser.Attr_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link DOTBPMParser#id}.
	 * @param ctx the parse tree
	 */
	void enterId(DOTBPMParser.IdContext ctx);
	/**
	 * Exit a parse tree produced by {@link DOTBPMParser#id}.
	 * @param ctx the parse tree
	 */
	void exitId(DOTBPMParser.IdContext ctx);
}