package com.ubiqube.etsi.mano.service.event;

import java.util.Map;
import java.util.UUID;

import javax.annotation.Nonnull;

/**
 * Manage Asynchronous Event in the application. Current implementation is using
 * Quartz using it's thread pool, but we ill probably need an entreprise bus.
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public interface EventManager {
	/**
	 * Send MANO notifications.
	 *
	 * @param notificationEvent The notification event type.
	 * @param objectId          The Id.
	 */
	void sendNotification(@Nonnull NotificationEvent notificationEvent, @Nonnull UUID objectId);

	/**
	 * Create an asynchronous task.
	 *
	 * @param actionType The type of the action.
	 * @param objectId   The object Id.
	 * @param parameters Additional parameters if any.
	 */
	void sendAction(@Nonnull ActionType actionType, @Nonnull UUID objectId, @Nonnull Map<String, Object> parameters);

	/**
	 * Send an asynchronous grant message.
	 * 
	 * @param objectId   The grantResponse Id.
	 * @param parameters Additional parameters if any.
	 */
	void sendGrant(final UUID objectId, final Map<String, Object> parameters);
}
