package com.mulesoft.init.listner;


import org.mule.DefaultMuleContext;
import org.mule.DefaultMuleEvent;
import org.mule.DefaultMuleMessage;
import org.mule.MessageExchangePattern;
import org.mule.api.MuleException;
import org.mule.api.context.notification.MuleContextNotificationListener;
import org.mule.construct.Flow;
import org.mule.context.notification.MuleContextNotification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class FlowInvokingListner implements MuleContextNotificationListener<MuleContextNotification>
{
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
    private Flow startingFlow;
    

	public void onNotification(final MuleContextNotification notification)
    {
        if (notification.getAction() == MuleContextNotification.CONTEXT_STARTED)
        {
            sendNotificationToFlow(notification, startingFlow);
        }
    }

	private void sendNotificationToFlow(final MuleContextNotification notification, final Flow flow)
    {
        try
        {
        	logger.debug("Starting Flow ("+flow.getName()+") with injected Flow reference ("+startingFlow+")");
        	DefaultMuleMessage message = new DefaultMuleMessage(notification, notification.getMuleContext());
        	
        	final DefaultMuleEvent event = new DefaultMuleEvent(message, MessageExchangePattern.REQUEST_RESPONSE, startingFlow);
            flow.process(event);
        }
        catch (final MuleException me)
        {
        	logger.error("Failed init flow execution");
        	DefaultMuleContext dMC = (DefaultMuleContext)notification.getMuleContext();
        	dMC.dispose();
        	System.exit(1);
        }
        
    }
	
	public void setStartingFlow(final Flow startingFlow)
    {
        this.startingFlow = startingFlow;
    }

	
}