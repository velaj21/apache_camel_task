# Use the official RabbitMQ image as the base
FROM rabbitmq:3-management

# Expose the necessary ports
EXPOSE 5672 15672

# Set the default username and password for the RabbitMQ management interface
ENV RABBITMQ_DEFAULT_USER guest
ENV RABBITMQ_DEFAULT_PASS guest

# Start the RabbitMQ server
CMD ["rabbitmq-server"]
