import UIKit

@objc public protocol SwipeHandlerDelegate {
    @objc func onSwipeDetected()
}

@objc public class SwipeHandler: NSObject {
    private weak var delegate: SwipeHandlerDelegate?
    private var swipeGesture: UISwipeGestureRecognizer?

    @objc public init(view: UIView, delegate: SwipeHandlerDelegate) {
        self.delegate = delegate
        super.init()

        let gesture = UISwipeGestureRecognizer(target: self, action: #selector(handleSwipe))
        gesture.direction = .right
        view.addGestureRecognizer(gesture)
        self.swipeGesture = gesture
    }

    @objc func handleSwipe() {
        delegate?.onSwipeDetected()
    }

    @objc public func removeGesture(from view: UIView) {
        if let gesture = swipeGesture {
            view.removeGestureRecognizer(gesture)
        }
    }
}