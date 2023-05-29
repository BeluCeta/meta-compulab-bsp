if [ -z "$SSH_CLIENT" ] && [ -z "$SSH_TTY" ]; then
    # We're not running over SSH connection, i.e. in a local terminal, so run the resize command
    shopt -s checkwinsize
    command -v resize &>/dev/null && resize
fi
