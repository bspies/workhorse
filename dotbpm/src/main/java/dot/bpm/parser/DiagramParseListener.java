package dot.bpm.parser;

import dot.bpm.diagram.ProcessDiagram;
import dot.bpm.parser.antlr.DOTBPMBaseListener;
import dot.bpm.parser.antlr.DOTBPMParser;

class DiagramParseListener extends DOTBPMBaseListener {

    private ProcessDiagram diagram;

    @Override public void enterProcess(DOTBPMParser.ProcessContext ctx) {
        diagram = new ProcessDiagram();
    }
}
