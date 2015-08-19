package org.workhorse.sequence;

import org.junit.Before;
import org.junit.Test;
import org.workhorse.exec.ctx.Context;
import org.workhorse.exec.Execution;
import org.workhorse.exec.Phase;
import org.workhorse.expr.Condition;
import org.workhorse.sequence.impl.LinearSequence;
import org.workhorse.sequence.impl.LoopSequence;

import static org.easymock.EasyMock.*;

/**
 * Tests for the sequence framework.
 *
 * @author Brennan Spies
 */
public class SequenceTest
{
	private Context mockCtx;
	private Execution mockExec;
	
	/**
     * Test setup.
	 * @throws java.lang.Exception If an error occurs
	 */
	@Before
	public void setUp() throws Exception {
		mockCtx = createMock(Context.class);
		mockExec = createMock(Execution.class);
		expect(mockExec.getExecutionContext()).andReturn(mockCtx).anyTimes();
		replay(mockExec);
	}
	
	@Test
	public void testLinearSequence() 
	{
		Step step = createStrictMock(Step.class);
		expect(step.perform(mockExec)).andReturn(true).times(3);
		replay(step);
		
		SequenceBuilder sequenceBuilder = LinearSequence.newBuilder();
		sequenceBuilder
			.add(step)
			.add(step)
			.add(step);
		Sequence sequence = sequenceBuilder.build();
		sequence.perform(mockExec);
        verify(step);
	}
	
	@Test
	public void testLinearSequenceWithReentry() 
	{
		Step step = createStrictMock(Step.class);
		expect(step.perform(mockExec)).andReturn(true);
		expect(step.perform(mockExec)).andReturn(false);
		expect(step.perform(mockExec)).andReturn(true).once();
        expect(step.perform(mockExec)).andReturn(false).once();
		replay(step);
		
		SequenceBuilder sequenceBuilder = LinearSequence.newBuilder();
		sequenceBuilder
			.add(Phase.ENTER, step)
			.add(Phase.PROCESS, step)
			.add(Phase.POST_PROCESS, step)
			.add(Phase.LEAVE, step);
		Sequence sequence = sequenceBuilder.build();
		sequence.perform(mockExec);
		//re-entry
		sequence.reenter(Phase.POST_PROCESS, mockExec);
		verify(step);
	}
	
	@Test
	public void testWhileLoop() 
	{
		Step step = createMock(Step.class);
		expect(step.perform(mockExec)).andReturn(true).anyTimes();
		replay(step);
		
		Condition loopCondition = createStrictMock(Condition.class);
		expect(loopCondition.evaluate(mockCtx)).andReturn(true).times(2);
		expect(loopCondition.evaluate(mockCtx)).andReturn(false);
		replay(loopCondition);
		
		SequenceBuilder sequenceBuilder = LoopSequence.newBuilder(loopCondition, true);
		sequenceBuilder.add(step).add(step).add(step).build().perform(mockExec);
        verify(loopCondition);
	}
	
	@Test
	public void testWhileLoopWithReentry() {
		Step step = createStrictMock(Step.class);
		expect(step.perform(mockExec)).andReturn(true);
		expect(step.perform(mockExec)).andReturn(false);
		expect(step.perform(mockExec)).andReturn(true).anyTimes();
		replay(step);
		
		Condition loopCondition = createStrictMock(Condition.class);
		expect(loopCondition.evaluate(mockCtx)).andReturn(true);
		expect(loopCondition.evaluate(mockCtx)).andReturn(false);
		replay(loopCondition);
		
		SequenceBuilder sequenceBuilder = LoopSequence.newBuilder(loopCondition, true);
		sequenceBuilder
			.add(Phase.ENTER, step)
			.add(Phase.PROCESS, step)
			.add(Phase.POST_PROCESS, step)
			.add(Phase.LEAVE, step);
		Sequence sequence = sequenceBuilder.build();
		sequence.perform(mockExec);
		//re-entry
		sequence.reenter(Phase.POST_PROCESS, mockExec);
		verify(step);
        verify(loopCondition);
	}
	
	@Test
	public void testUntilLoop() 
	{
		Step step = createMock(Step.class);
		expect(step.perform(mockExec)).andReturn(true).anyTimes();
		replay(step);
		
		Condition loopCondition = createMock(Condition.class);
		expect(loopCondition.evaluate(mockCtx)).andReturn(true).times(2);
		expect(loopCondition.evaluate(mockCtx)).andReturn(false);
		replay(loopCondition);
		
		SequenceBuilder sequenceBuilder = LoopSequence.newBuilder(loopCondition, false);
		sequenceBuilder.add(step).add(step).add(step).build().perform(mockExec);
        verify(loopCondition);
	}
}
