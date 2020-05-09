package com.ubiqube.etsi.mano.service.event;

import java.util.Map;

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
	void sendNotification(@Nonnull NotificationEvent notificationEvent, @Nonnull String objectId);

	/**
	 * Create an asynchronous task.
	 *
	 * @param actionType The type of the action.
	 * @param objectId   The object Id.
	 * @param parameters Additional parameters if any.
	 */
	void sendAction(@Nonnull ActionType actionType, @Nonnull String objectId, @Nonnull Map<String, Object> parameters);
}
