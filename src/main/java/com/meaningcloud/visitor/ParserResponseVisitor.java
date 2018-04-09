package com.meaningcloud.visitor;

import com.meaningcloud.ParserResponse;

import java.util.List;

/**
 * Class to contain a tree of nodes
 */
public class ParserResponseVisitor {

    /**
     * Collect data about the token
     * @param list A ParserResponse.Token list
     */
    protected void visitTokens(List<ParserResponse.Token> list) {
        if (list != null) {
            for (ParserResponse.Token t : list) {
                visit(t);
            }
        }
    }

    /**
     * Collect data about the analysis
     * @param list A ParserResponse.Analysis list
     */
    protected void visitAnalyses(List<ParserResponse.Analysis> list) {
        if (list != null) {
            for (ParserResponse.Analysis t : list) {
                visit(t);
            }
        }
    }

    /**
     * Collect data about the sense
     * @param list
     */
    protected void visitSenses(List<ParserResponse.Sense> list) {
        if (list != null) {
            for (ParserResponse.Sense t : list) {
                visit(t);
            }
        }
    }

    /**
     * Collect data about the syntactic relations
     * @param list A ParserResponse.TeeRelation list
     */
    protected void visitTreeRelations(List<ParserResponse.TreeRelation> list) {
        if (list != null) {
            for (ParserResponse.TreeRelation t : list) {
                visit(t);
            }
        }
    }

    /**
     * Collect data about sense identifiers
     * @param list A ParserResponse.SenseId list
     */
    protected void visitSenseIds(List<ParserResponse.SenseId> list) {
        if (list != null) {
            for (ParserResponse.SenseId t : list) {
                visit(t);
            }
        }
    }

    /**
     * Collect data about response to Lemmatization, PoS and Parsing
     * @param resp A response to Lemmatization, PoS and Parsing
     */
    public void visit(ParserResponse resp) {
        visitTokens(resp.getTokenList());
    }

    /**
     * Collect data about elemental tokens that will provide the morphological analysis
     * @param token Elemental tokens that will provide the morphological analysis
     */
    public void visit(ParserResponse.Token token) {
        visitTokens(token.getTokenList());
        visitAnalyses(token.getAnalysisList());
        visitSenses(token.getSenseList());
        visitTreeRelations(token.getSyntacticTreeRelationList());
    }

    /**
     * Collect data about how we will represent each node of our morphosyntactic tree
     * @param analysis How we will represent each node of our morphosyntactic tree
     */
    public void visit(ParserResponse.Analysis analysis) {
        visitSenseIds(analysis.getSenseIdList());
    }

    /**
     * Collect data about sense identifiers
     * @param senseId Sense identifiers
     */
    public void visit(ParserResponse.SenseId senseId) {
    }

    /**
     * Collect data about the syntactic relations
     * @param t The syntactic relations
     */
    public void visit(ParserResponse.TreeRelation t) {
    }

    /**
     * Collect data about senses or semantic analyses associated to the token
     * @param sense Senses or semantic analyses associated to the token
     */
    public void visit(ParserResponse.Sense sense) {
    }
}